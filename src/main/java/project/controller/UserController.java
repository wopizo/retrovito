package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.domain.Review;
import project.domain.User;
import project.service.ReviewService;
import project.service.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;

    @GetMapping("{user}")
    public String createPage(@PathVariable User user, Model model){
        Iterable<Review> reviews = reviewService.getAll(user);
        model.addAttribute("usr", user);
        model.addAttribute("reviews", reviews);
        model.addAttribute("rating", reviewService.rating(reviews));
        if(user.getId() == -1){
            return "redirect:/";
        }
        return "user";
    }

    @PostMapping("addReview")
    public String addReview(
            @AuthenticationPrincipal User user,
            Review review,
            Model model){
        reviewService.addReview(review, user);
        return "redirect:/user/" + review.getUserTo().getId();

    }

    @PostMapping("editReview")
    public String editReview(
            @RequestParam String message,
            @RequestParam Integer mark,
            @RequestParam Long reviewId,
            Model model){
        Review review = reviewService.getOne(reviewId);
        reviewService.edit(review, mark, message);
        return "redirect:/user/" + review.getUserTo().getId();
    }

    @GetMapping("/editUser")
    public String editUserPage(
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("usr", user);
        return "editUser";
    }

    @PostMapping("/editUser")
    public String editUser(
            @AuthenticationPrincipal User user,
            User newUser,
            Model model,
            @RequestParam("pic") MultipartFile picture) throws IOException{
        if(!userService.editUser(user, newUser, picture)){
            model.addAttribute("usr", user);
            model.addAttribute("error", true);
            return "editUser";
        }
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/editPassword")
    public String editUserPage(Model model){
        return "editPassword";
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/editPassword")
    public String editUser(
            @AuthenticationPrincipal User user,
            @RequestParam String new1,
            @RequestParam String new2,
            Model model){
        if(new1 == null || new1.equals("") || !new1.equals(new2)){
            model.addAttribute("error", true);
            return "editPassword";
        }
        userService.editPassword(user, new1);
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("removeReview")
    public String removeReview(@RequestParam Long reviewId, Model model){
        long review = reviewService.getOne(reviewId).getUserTo().getId();
        reviewService.removeReview(reviewId);
        return "redirect:/user/" + review;
    }
}
