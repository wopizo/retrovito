package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.dao.DealDAO;
import project.domain.*;
import project.repos.AdvertRepo;
import project.repos.UserRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private DealDAO dealDAO;
    @Autowired
    AdvertService advertService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MessageService messageService;
    @Autowired
    CommentService commentService;
    @Autowired
    MailSender mailSender;


    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(!user.isActive()){
            return null;
        }
        return user;
    }

    public boolean addUser(User user, MultipartFile picture) throws IOException {
        User userByLogin = userRepo.findByUsername(user.getUsername());
        User userByEmail = userRepo.findByEmail(user.getEmail());
        if(userByLogin != null || userByEmail != null){
            return false;
        }

        if (picture != null && !picture.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath + "/userImages");

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + picture.getOriginalFilename();

            picture.transferTo(new File(uploadPath + "/userImages/" + resultFileName));

            user.setPicture(resultFileName);
        }

        user.setActive(false);
        user.setActivationMessage(UUID.randomUUID().toString());
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        if(user.getEmail() != null && !user.getEmail().equals("")){
            String message = String.format(
                    "Добрый день, %s! \n" +
                            "Добро пожаловать в retrovito. Для активации вашего аккаунта, пожалуйста, перейдите по ссылке: http://localhost:8080/activate/%s",
                    user.getName(),
                    user.getActivationMessage()
            );

            mailSender.send(user.getEmail(), "Активация аккаунта", message);
        }

        return true;
    }

    public void giveAdmin(Long id){
        userRepo.getOne(id).getRoles().add(Role.ADMIN);
    }

    public void removeAdmin(Long id){
        userRepo.getOne(id).getRoles().remove(Role.ADMIN);
    }

    public void giveBan(Long id){
        User user = userRepo.getOne(id);
        user.getRoles().clear();
        user.getRoles().add(Role.BLOCKED);
        List<Advert> list = advertService.getAllUserAdverts(user);
        for(Advert a: list){
            a.setActive(false);
        }
    }

    public void removeBan(Long id){
        User user = userRepo.getOne(id);
        user.getRoles().clear();
        user.getRoles().add(Role.USER);
        List<Advert> allAdverts = advertService.getAllUserAdverts(user);
        List<Deal> dealAdverts = dealDAO.getAllByAuthor(id);
        for(Advert a: allAdverts){
            a.setActive(true);
            for(Deal d: dealAdverts){
                if(d.getAdvert().getId() == a.getId()){
                    a.setActive(false);
                }
            }
        }
    }

    public void removeUser(Long id){
        User user = userRepo.getOne(id);
        user.getRoles().clear();
        user.setActive(false);
        List<Advert> allAdverts = advertService.getAllUserAdverts(user);
        List<Deal> dealAdverts = dealDAO.getAllByAuthor(id);
        for(Advert a: allAdverts){
            a.setActive(true);
            for(Deal d: dealAdverts){
                if(d.getAdvert().getId() == a.getId()){
                    a.setActive(false);
                }
            }
            if(a.isActive()){
                advertService.removeAdvert(a.getId());
            }
        }

        for(Review r: reviewService.getAll(user)){
            reviewService.removeReview(r.getId());
        }

        for(Comment c: commentService.getAllByUser(user)){
            commentService.removeComment(c.getId());
        }

        for(Review r: reviewService.getAllFrom(user)){
            reviewService.removeReview(r.getId());
        }

        List<User> users = new ArrayList<>();
        for (Message m: messageService.getUserChats(user)){
            if(m.getUserTo().getId() == user.getId()){
                users.add(m.getUserFrom());
            }else{
                users.add(m.getUserTo());
            }
        }
        for (User u: users){
            for (Message m: messageService.getThisToUser(user, u)){
                messageService.removeMessage(m.getId());
            }
        }
    }

    public User getTechUser(){
        return userRepo.getOne(new Long(-1));
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationMessage(code);

        if (user == null){
            return false;
        }

        user.setActivationMessage(null);
        user.setActive(true);
        userRepo.save(user);

        return true;
    }

    public void restoreAccount(User user) {
        if(user.getEmail() != null && !user.getEmail().equals("")){
            String newPassword = UUID.randomUUID().toString();
            user.setPassword(passwordEncoder.encode(newPassword));

            String message = String.format(
                    "Добрый день, %s! \n" +
                            "Ваш логин: %s \n" +
                            "Ваш новый пароль: %s \n",
                    user.getName(),
                    user.getUsername(),
                    newPassword
            );

            mailSender.send(user.getEmail(), "Восстановления аккаунта", message);

            userRepo.save(user);
        }
    }

    public User getByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public boolean editUser(User user, User newUser, MultipartFile picture) throws IOException {
        if(newUser.getEmail() != null && !newUser.getEmail().equals("")){
            User userFromDB = userRepo.findByEmail(newUser.getEmail());
            if(userFromDB == null || userFromDB.getEmail().equals(newUser.getEmail())){
                user.setEmail(newUser.getEmail());
            }else{
                return false;
            }
        }else{
            return false;
        }

        if(newUser.getName() != null && !newUser.getName().equals("")){
            user.setName(newUser.getName());
        }else{
            return false;
        }

        if (picture != null && !picture.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath + "/userImages");

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + picture.getOriginalFilename();

            picture.transferTo(new File(uploadPath + "/userImages/" + resultFileName));

            user.setPicture(resultFileName);
        }

        user.setSname(newUser.getSname());
        user.setFname(newUser.getFname());
        user.setPhone(newUser.getPhone());
        user.setCity(newUser.getCity());
        userRepo.save(user);
        return true;
    }

    public void editPassword(User user, String new1) {
        user.setPassword(passwordEncoder.encode(new1));
        userRepo.save(user);
    }
}
