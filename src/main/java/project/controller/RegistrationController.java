package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.domain.User;
import project.service.UserService;

import java.io.IOException;

@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          Model model,
                          @RequestParam("pic") MultipartFile picture) throws IOException{

        boolean successfulAdd = userService.addUser(user, picture);

        if(!successfulAdd){
            model.addAttribute("message", "Пользователь с таким логином или почтой уже зарегестрирован!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activation(@PathVariable String code, Model model){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("message", 1);
        }else{
            model.addAttribute("message", 0);
        }

        return "login";
    }

    @GetMapping("/restore")
    public String restorePage(Model model){
        return "restore";
    }

    @PostMapping("/restore")
    public String restore(@RequestParam String email, Model model){
        User user = userService.getByEmail(email);
        if(user == null){
            model.addAttribute("message", 0);
        }else {
            userService.restoreAccount(user);
            model.addAttribute("message", 2);
        }
        return "login";
    }


}
