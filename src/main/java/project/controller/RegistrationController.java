package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.Role;
import project.domain.User;
import project.repos.UserRepo;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String addUser(User user,
                          @RequestParam String name,
                          @RequestParam String sname,
                          @RequestParam String fname,
                          @RequestParam String email,
                          @RequestParam String phone,
                          @RequestParam String city,
                          Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.addAttribute("message", "Пользователь с таким логином уже зарегестрирован!");
            return "registration";
        }

        user.setActive(true);
        user.setBlocked(false);
        user.setName(name);
        user.setSname(sname);
        user.setFname(fname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCity(city);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return "redirect:/login";
    }

}
