package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.domain.Role;
import project.domain.User;
import project.repos.UserRepo;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean uddUser(User user, MultipartFile picture) throws IOException {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
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

        user.setActive(true);
        user.setBlocked(false);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return true;
    }
}
