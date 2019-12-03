package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.domain.Review;
import project.domain.User;
import project.service.ReviewService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("{user}")
    public String createPage(@PathVariable User user, Model model){
        Iterable<Review> reviews = reviewService.getAll(user);
        model.addAttribute("usr", user);
        model.addAttribute("reviews", reviews);
        model.addAttribute("rating", reviewService.rating(reviews));
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
}
