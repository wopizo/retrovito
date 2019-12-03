package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import project.dao.AdvertDAO;
import project.domain.Advert;
import project.domain.User;
import project.repos.AdvertRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AdvertService {

    @Autowired
    private AdvertRepo advertRepo;

    @Autowired
    private AdvertDAO advertDAO;

    @Value("${upload.path}")
    private String uploadPath;

    public void addAdvert(User author, Advert advert, MultipartFile picture) throws IOException {

        advert.setAuthor(author);
        advert.setActive(true);
        advert.setHasClient(false);

        if (picture != null && !picture.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath + "/advImages");

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + picture.getOriginalFilename();

            picture.transferTo(new File(uploadPath + "/advImages/" + resultFileName));

            advert.setPicture(resultFileName);
        }

        advertRepo.save(advert);
    }

    public Iterable<Advert> getAll() {
        return advertRepo.findAll();
    }


    public Iterable<Advert> search(String search, Iterable<Advert> adverts) {
        if (search != null && !search.equals("")) {
            List<Advert> results = new ArrayList<>();
            for (Advert a : adverts) {
                if (a.getTittle().toLowerCase().contains(search.toLowerCase())){
                    results.add(a);
                }
            }
            return results;
        }
        return adverts;
    }

    public List<Advert> filterAndSort(Long froom, Long too, String type, String company, String sort){
        if(type.equals(""))type=null;
        if(company.equals(""))company=null;
        return advertDAO.filterAndSort(froom, too, type, company, sort);
    }

    public boolean validateCreation(Advert advert, Model model) {
        List<String> errors = new ArrayList<>();

        if (advert.getTittle() == null || advert.getTittle().equals("")) {
            model.addAttribute("tittleError", "Введите заголовок");
            errors.add("Введите заголовок");
        }

        if (advert.getCost() == null || advert.getCost().equals("")) {
            model.addAttribute("costError", "Укажите цену вашего лота");
            errors.add("Укажите цену вашего лота");
        }else{
            try {
                Long.parseLong(advert.getCost().toString());
            } catch (NumberFormatException nfe) {
                errors.add("Цена может быть только целым числом");
            }
        }

        if (advert.getType() == null || advert.getType().equals("")) {
            errors.add("Укажите тип лота");
        }
        if (advert.getCompany() == null || advert.getCompany().equals("")) {
            errors.add("Укажите производителя");
        }
        if (advert.getCity() == null || advert.getCity().equals("")) {
            model.addAttribute("cityError", "Укажите город, в котором находится товар");
            errors.add("Укажите город, в котором находится товар");
        }
        model.addAttribute("errors", errors);
        return errors.isEmpty();
    }

}
