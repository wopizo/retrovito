package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.Advert;
import project.domain.filters.Company;
import project.domain.filters.Type;
import project.service.AdvertService;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private AdvertService advertService;

    @GetMapping("/main")
    public String mainPage(@RequestParam String search,
                           @RequestParam Long froom,
                           @RequestParam Long too,
                           @RequestParam String type,
                           @RequestParam String company,
                           Model model) {
        Iterable<Advert> adverts = advertService.search(search,froom,too,type,company);
        model.addAttribute("adverts", adverts);
        model.addAttribute("types", new ArrayList<Type>(Arrays.asList(Type.values())));
        model.addAttribute("companies", new ArrayList<Company>(Arrays.asList(Company.values())));
        return "main";
    }

    @GetMapping
    public String mainPageAgain(Model model) {
        return mainPage(null, null, null, "","", model);
    }

}
