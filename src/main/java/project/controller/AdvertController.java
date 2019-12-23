package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.domain.Advert;
import project.domain.Comment;
import project.domain.User;
import project.repos.AdvertRepo;
import project.repos.CommentRepo;
import project.service.AdvertService;
import project.service.CommentService;

import java.util.List;

@Controller
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    CommentService commentService;
    @Autowired
    AdvertService advertService;

    @GetMapping("{advert}")
    public String createPage(@PathVariable Advert advert, Model model){
        model.addAttribute("advert", advert);
        model.addAttribute("comments", commentService.getAll(advert));
        return "advert";
    }

    @PostMapping("addComment")
    public String addComment(
            @AuthenticationPrincipal User user,
            Comment comment,
            Model model){
        commentService.addComment(comment,user);
        return "redirect:/advert/" + comment.getAdvert().getId();
    }

    @PostMapping("editComment")
    public String editComment(
            @RequestParam String message,
            @RequestParam Long commentId,
            Model model){
        Comment comment = commentService.getOne(commentId);
        commentService.edit(comment, message);
        return "redirect:/advert/" + comment.getAdvert().getId();
    }

    @PostMapping("removeAdvert")
    public String removeAdvert(@RequestParam Long advertId, Model model){
        advertService.removeAdvert(advertId);
        return "redirect:/";
    }

    @PostMapping("removeComment")
    public String removeComment(@RequestParam Long commentId, Model model){
        long advert = commentService.getOne(commentId).getAdvert().getId();
        commentService.removeComment(commentId);
        return "redirect:/advert/" + advert;
    }
}
