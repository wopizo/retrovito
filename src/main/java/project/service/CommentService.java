package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Advert;
import project.domain.Comment;
import project.domain.User;
import project.repos.CommentRepo;

@Service
@Transactional
public class CommentService{
    @Autowired
    CommentRepo commentRepo;

    public Iterable<Comment> getAll(Advert advert){
        return commentRepo.findByAdvertOrderByDateDesc(advert);
    }

    public boolean addComment(Comment comment, User user){
        comment.setUserFrom(user);
        if(comment.getMessage() != null && !comment.getMessage().equals("")){
            commentRepo.save(comment);
            return true;
        }
        return false;
    }
}
