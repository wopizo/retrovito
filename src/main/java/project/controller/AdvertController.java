package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.domain.Advert;

@Controller
@RequestMapping("/advert")
public class AdvertController {

    @GetMapping("{advert}")
    public String createPage(@PathVariable Advert advert, Model model){
        model.addAttribute("advert", advert);
        return "advert";
    }
}
