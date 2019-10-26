package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.Role;
import project.domain.User;
import project.repos.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/main")
    public String mainPage(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          @RequestParam String name,
                          @RequestParam String sname,
                          @RequestParam String fname,
                          @RequestParam String email,
                          @RequestParam String phone,
                          @RequestParam String city,
                          Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.put("message", "User exists!");
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
