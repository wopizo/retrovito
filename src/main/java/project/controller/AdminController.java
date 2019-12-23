package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.domain.*;
import project.service.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    AdvertService advertService;

    @Autowired
    CommentService commentService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/techChat")
    public String showTechChats(Model model){
        User userTech = userService.getTechUser();
        List<Message> chats = messageService.getUserChats(userTech);
        model.addAttribute("chats", chats);
        model.addAttribute("techChat", true);
        return "chat";
    }

    @GetMapping("/techChat/{toUser}")
    public String createTechChat(@PathVariable User toUser, Model model){
        User userTech = userService.getTechUser();
        List<Message> messages = messageService.getThisToUser(userTech, toUser);
        messageService.checkedAllMessages(messages, userTech);
        model.addAttribute("messages", messages);
        model.addAttribute("thisUser", userTech);
        model.addAttribute("toUser", toUser);
        model.addAttribute("techChat", true);
        return "chatTo";
    }

    @PostMapping("/techChat")
    public String add(
            @RequestParam("userTo") Long toUserId,
            @RequestParam("message") String message,
            Model model){
        User userTech = userService.getTechUser();
        model.addAttribute("techChat", true);
        boolean successfulAdd = messageService.addMessage(userTech, toUserId, message);

        if(!successfulAdd){
            model.addAttribute("error", "Нельзя отправить пустое сообщение");
        }
        return "redirect:/techChat/" + toUserId;
    }

    @GetMapping("adminShow")
    public String showStatistic(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("search") String search,
            Model model){
        List<User> users = adminService.search(search, adminService.getUsers(from,to));
        List<Advert> adverts = adminService.search(search, adminService.getAdverts(from,to));
        List<Comment> comments = adminService.search(search, adminService.getComments(from,to));
        List<Review> reviews = adminService.search(search, adminService.getReviews(from,to));
        List<Deal> deals = adminService.search(search, adminService.getDeals(from,to));
        model.addAttribute("users", users);
        model.addAttribute("adverts", adverts);
        model.addAttribute("comments", comments);
        model.addAttribute("reviews", reviews);
        model.addAttribute("deals", deals);
        model.addAttribute("userCount", adminService.count(users));
        model.addAttribute("advertCount", adminService.count(adverts));
        model.addAttribute("commentCount", adminService.count(comments));
        model.addAttribute("reviewCount", adminService.count(reviews));
        model.addAttribute("dealCount", adminService.count(deals));
        return "admin";
    }

    @GetMapping("/admin")
    public String createPage(Model model){
        return showStatistic(null,null, null, model);
    }

    @PostMapping("/admin")
    public String change(
            @RequestParam String mode,
            @RequestParam String entity,
            @RequestParam Long id,
            Model model){
        if(entity.equals("user")){
            if (mode.equals("giveAdmin")){
                userService.giveAdmin(id);
            }else if(mode.equals("removeAdmin")){
                userService.removeAdmin(id);
            }else if(mode.equals("giveBan")){
                userService.giveBan(id);
            }else if(mode.equals("removeBan")){
                userService.removeBan(id);
            }else{
                userService.removeUser(id);
            }
        }else if(entity.equals("advert")){
            if(mode.equals("giveBan")){
                advertService.giveBan(id);
            }else if(mode.equals("removeBan")){
                advertService.removeBan(id);
            }else{
                advertService.removeAdvert(id);
            }
        }else if(entity.equals("comment")){
            commentService.removeComment(id);
        }else if(entity.equals("review")){
            reviewService.removeReview(id);
        }
        return "redirect:/admin";
    }
}
