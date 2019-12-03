package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Review;
import project.domain.User;
import project.repos.ReviewRepo;

@Service
@Transactional
public class ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    public Iterable<Review> getAll(User userTo) {
        return reviewRepo.findByUserToOrderByDateDesc(userTo);
    }

    public boolean addReview(Review review, User userFrom) {
        review.setUserFrom(userFrom);
        if (review.getMessage() != null && !review.getMessage().equals("")) {
            if (reviewRepo.findReviewByUserFromAndUserTo(review.getUserFrom(), review.getUserTo()) == null
            && review.getUserTo().getId() != review.getUserFrom().getId()) {
                reviewRepo.save(review);
                return true;
            }
        }
        return false;
    }

    public int rating(Iterable<Review> reviews){
        int count = 0;
        for (Review r : reviews){
            if(r.isMark())
                count++;
            else
                count--;
        }
        return count;
    }

}
