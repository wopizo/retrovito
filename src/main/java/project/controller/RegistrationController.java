package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.User;
import project.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

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

        boolean successfulAdd = userService.uddUser(user, name, sname, fname, email, phone, city);

        if(!successfulAdd){
            model.addAttribute("message", "Пользователь с таким логином уже зарегестрирован!");
            return "registration";
        }

        return "redirect:/login";
    }

}
