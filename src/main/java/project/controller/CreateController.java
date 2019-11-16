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
import project.domain.User;
import project.service.AdvertService;

import java.io.IOException;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    AdvertService advertService;

    @GetMapping
    public String createPage() {
        return "create";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User author,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam String tittle,
            @RequestParam String cost,
            @RequestParam String type,
            @RequestParam String company,
            @RequestParam String city,
            @RequestParam String description,
            Model model) throws IOException {

        advertService.addAdvert(author, picture, tittle, cost, type, company, city, description);

        return "redirect:/main";
    }

}
