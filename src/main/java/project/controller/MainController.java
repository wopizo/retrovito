package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.domain.Advert;
import project.service.AdvertService;

@Controller
public class MainController {

    @Autowired
    private AdvertService advertService;


    @GetMapping("/main")
    public String mainPage(Model model) {
        Iterable<Advert> adverts = advertService.getAll();
        model.addAttribute("adverts", adverts);
        return "main";
    }

    @GetMapping("/")
    public String mainPageAgain(Model model) {
        return mainPage(model);
    }

}
