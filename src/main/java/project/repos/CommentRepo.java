package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Advert;
import project.domain.Comment;
import project.domain.User;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
        List<Comment> findByAdvertOrderByDateDesc(Advert advert);
        List<Comment> findByUserFrom(User user);
}
