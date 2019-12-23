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

    public Iterable<Comment> getAllByUser(User user){
        return commentRepo.findByUserFrom(user);
    }

    public boolean addComment(Comment comment, User user){
        comment.setUserFrom(user);
        if(comment.getMessage() != null && !comment.getMessage().equals("")){
            comment.setEdited(false);
            commentRepo.save(comment);
            return true;
        }
        return false;
    }

    public void removeComment(Long id){
        commentRepo.deleteById(id);
    }

    public Comment getOne(Long id){return commentRepo.findById(id).get();}

    public void edit(Comment comment, String message) {
        if(message != null && !message.equals("")){
            comment.setMessage(message);
            comment.setEdited(true);
            commentRepo.save(comment);
        }
    }
}
