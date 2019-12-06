package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.DealDAO;
import project.domain.Advert;
import project.domain.Deal;
import project.domain.User;
import project.repos.DealRepo;

import java.util.List;

@Service
@Transactional
public class DealService {

    @Autowired
    DealRepo dealRepo;

    @Autowired
    DealDAO dealDAO;

    public boolean addDeal(Advert advert) {

        advert.setActive(false);
        dealRepo.save(new Deal(advert));

        return true;
    }

    public List<Deal> showDealsByAuthor(User user){
        return dealDAO.getAllByAuthor(user.getId());
    }

    public List<Deal> showDealsByBuyer(User user){
        return dealDAO.getAllByBuyer(user.getId());
    }
}
