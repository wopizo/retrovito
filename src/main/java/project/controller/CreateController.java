package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.domain.Advert;
import project.domain.User;
import project.domain.filters.Company;
import project.domain.filters.Type;
import project.service.AdvertService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    AdvertService advertService;

    @GetMapping
    public String createPage(Model model) {
        model.addAttribute("types", new ArrayList<Type>(Arrays.asList(Type.values())));
        model.addAttribute("companies", new ArrayList<Company>(Arrays.asList(Company.values())));
        return "create";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User author,
            Advert advert,
            Model model,
            @RequestParam("pic") MultipartFile picture) throws IOException {


        if (!advertService.validateCreation(advert, model)) {
            model.addAttribute("types", new ArrayList<Type>(Arrays.asList(Type.values())));
            model.addAttribute("companies", new ArrayList<Company>(Arrays.asList(Company.values())));
            model.addAttribute("advert", advert);
            return "create";
        }
        advertService.addAdvert(author, advert, picture);
        return "redirect:/";
    }

}
