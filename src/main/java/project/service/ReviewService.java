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
    public Iterable<Review> getAllFrom(User userFrom) {
        return reviewRepo.findByUserFrom(userFrom);
    }

    public boolean addReview(Review review, User userFrom) {
        review.setUserFrom(userFrom);
        if (review.getMessage() != null && !review.getMessage().equals("")) {
            if (reviewRepo.findReviewByUserFromAndUserTo(review.getUserFrom(), review.getUserTo()) == null
            && review.getUserTo().getId() != review.getUserFrom().getId()) {
                review.setEdited(false);
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

    public void removeReview(Long id){
        reviewRepo.delete(reviewRepo.findById(id).get());
    }

    public Review getOne(Long id){return reviewRepo.findById(id).get();}

    public void edit(Review review, int mark, String message) {
        if(message != null && !message.equals("")){
            review.setMessage(message);
            review.setEdited(true);
            if(mark == 1){
                review.setMark(true);
            } else {
                review.setMark(false);
            }
            reviewRepo.save(review);
        }
    }

}
