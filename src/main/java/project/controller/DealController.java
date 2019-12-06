package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.User;
import project.service.AdvertService;
import project.service.DealService;
import project.service.MessageService;

@Controller
public class DealController {

    @Autowired
    AdvertService advertService;

    @Autowired
    MessageService messageService;

    @Autowired
    DealService dealService;

    @GetMapping("/myAdverts")
    public String showMyAdverts(
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("chats", messageService.getUserChats(user));
        model.addAttribute("adverts", advertService.getAllUserAdverts(user));
        return "myAdverts";
    }

    @PostMapping("changeBuyer")
    public String changeBuyer(
            @RequestParam Long advert,
            @RequestParam Long buyer,
            Model model){
        advertService.changeBuyer(advert,buyer);
        return "redirect:/myAdverts";
    }

    @GetMapping("/myGoods")
    public String showMyGoods(
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("goods", advertService.getAllUserGoods(user));
        return "myGoods";
    }

    @PostMapping("addDeal")
    public String addDeal(
            @RequestParam Long advert,
            Model model){
        dealService.addDeal(advertService.getOne(advert));
        return "redirect:/myGoods";
    }

    @GetMapping("/myDeals")
    public String showMyDeals(
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("byBuyers", dealService.showDealsByBuyer(user));
        model.addAttribute("byAuthors", dealService.showDealsByAuthor(user));
        return "myDeals";
    }


}
