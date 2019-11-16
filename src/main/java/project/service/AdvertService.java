package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.domain.Advert;
import project.domain.User;
import project.repos.AdvertRepo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AdvertService {

    @Autowired
    private AdvertRepo advertRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public void addAdvert(User author, MultipartFile picture, String tittle, String cost,
                             String type, String company, String city, String description) throws IOException {

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
    }

    public Iterable<Advert> getAll(){
        return advertRepo.findAll();
    }

}
