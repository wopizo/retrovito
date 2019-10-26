package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import project.repos.AdvertRepo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    private AdvertRepo advertRepo;

    @Value("${upload.path}")
    private String uploadPath;

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

        Advert advert = new Advert(author, tittle, cost, type, company, city, description);

        if(picture != null && !picture.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + picture.getOriginalFilename();

            picture.transferTo(new File(uploadPath + "/" + resultFileName));

            advert.setPicture(resultFileName);
        }

        advertRepo.save(advert);

        return "redirect:/main";
    }

}
