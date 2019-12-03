package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.domain.Message;
import project.domain.User;
import project.service.MessageService;
import project.service.UserService;

import java.util.List;

@Controller
@RequestMapping
public class ChatController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @GetMapping("/chat")
    public String showAllChats(@AuthenticationPrincipal User user, Model model){
        List<Message> chats = messageService.getUserChats(user);
        model.addAttribute("chats", chats);
        return "chat";
    }

    @GetMapping("/chat/{toUser}")
    public String createChat(@AuthenticationPrincipal User thisUser,
                             @PathVariable User toUser,
                             Model model){
        List<Message> messages = messageService.getThisToUser(thisUser, toUser);
        messageService.checkedAllMessages(messages, thisUser);
        model.addAttribute("messages", messages);
        model.addAttribute("thisUser", thisUser);
        model.addAttribute("toUser", toUser);
        return "chatTo";
    }

    @PostMapping("/chat")
    public String add(
            @AuthenticationPrincipal User thisUser,
            @RequestParam("userTo") Long toUserId,
            @RequestParam("message") String message,
            Model model){

        boolean successfulAdd = messageService.uddMessage(thisUser, toUserId, message);

        if(!successfulAdd){
            model.addAttribute("error", "Нельзя отправить пустое сообщение");
        }
        return "redirect:/chat/" + toUserId;
    }


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
        boolean successfulAdd = messageService.uddMessage(userTech, toUserId, message);

        if(!successfulAdd){
            model.addAttribute("error", "Нельзя отправить пустое сообщение");
        }
        return "redirect:/techChat/" + toUserId;
    }
}
