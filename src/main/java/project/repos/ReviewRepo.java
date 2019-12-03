package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Review;
import project.domain.User;

import java.util.List;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Long> {
    List<Review> findByUserToOrderByDateDesc(User user);
    Review findReviewByUserFromAndUserTo(User userFrom, User userTo);

}
