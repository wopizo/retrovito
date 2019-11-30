package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.domain.User;
import project.service.UserService;

import java.io.IOException;

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
                          Model model,
                          @RequestParam("pic") MultipartFile picture) throws IOException{

        boolean successfulAdd = userService.uddUser(user, picture);

        if(!successfulAdd){
            model.addAttribute("message", "Пользователь с таким логином уже зарегестрирован!");
            return "registration";
        }

        return "redirect:/login";
    }

}
