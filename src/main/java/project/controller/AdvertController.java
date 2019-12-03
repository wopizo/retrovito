package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.domain.Advert;
import project.domain.Comment;
import project.domain.User;
import project.repos.AdvertRepo;
import project.repos.CommentRepo;
import project.service.CommentService;

import java.util.List;

@Controller
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    CommentService commentService;

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
}
