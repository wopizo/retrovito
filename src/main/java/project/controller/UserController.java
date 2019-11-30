package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("{user}")
    public String createPage(@PathVariable User user, Model model){
        model.addAttribute("usr", user);
        return "user";
    }
}
