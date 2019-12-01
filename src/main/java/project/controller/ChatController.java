package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.domain.Message;
import project.domain.User;
import project.service.MessageService;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public String showAllChats(@AuthenticationPrincipal User user, Model model){
        List<Message> chats = messageService.getUserChats(user);
        model.addAttribute("chats", chats);
        return "chat";
    }

    @GetMapping("{toUser}")
    public String createChat(@AuthenticationPrincipal User thisUser,
                             @PathVariable User toUser,
                             Model model){
        List<Message> messages = messageService.getThisToUser(thisUser, toUser);
        model.addAttribute("messages", messages);
        model.addAttribute("thisUser", thisUser);
        model.addAttribute("toUser", toUser);
        return "chatTo";
    }

    @PostMapping
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

}
