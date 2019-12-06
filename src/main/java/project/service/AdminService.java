package project.service;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.AdminDAO;
import project.domain.*;
import project.repos.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    UserRepo userRepo;
    @Autowired
    AdvertRepo advertRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    DealRepo dealRepo;

    public List<Advert> getAdverts(String from, String to) {
        if (from != null && from.equals("")) from = null;
        if (to != null && to.equals("")) to = null;
        return adminDAO.getAdverts(from, to);
    }

    public List<Comment> getComments(String from, String to) {
        if (from != null && from.equals("")) from = null;
        if (to != null && to.equals("")) to = null;
        return adminDAO.getComments(from, to);
    }

    public List<User> getUsers(String from, String to) {
        if (from != null && from.equals("")) from = null;
        if (to != null && to.equals("")) to = null;
        return adminDAO.getUsers(from, to);
    }

    public List<Review> getReviews(String from, String to) {
        if (from != null && from.equals("")) from = null;
        if (to != null && to.equals("")) to = null;
        return adminDAO.getReviews(from, to);
    }

    public List<Deal> getDeals(String from, String to) {
        if (from != null && from.equals("")) from = null;
        if (to != null && to.equals("")) to = null;
        return adminDAO.getDeals(from, to);
    }

    public <T> int count(List<T> list) {
        int count = 0;
        for (T t : list) {
            count++;
        }
        return count;
    }

    public <T> List<T> search(String search, List<T> list) {
        if (search != null && !search.equals("")) {
            List<T> newList = new ArrayList<>();
            for (T t : list) {
                String str = "";
                if (t instanceof Advert) {
                    str = ((Advert) t).getTittle();
                } else if (t instanceof User) {
                    if (((User) t).getSname() != null && !((User) t).getSname().equals("")) {
                        str += ((User) t).getSname() + " ";
                    }
                    str += ((User) t).getName();
                    if (((User) t).getFname() != null && !((User) t).getFname().equals("")) {
                        str += " " + ((User) t).getFname();
                    }
                } else if (t instanceof MessageModel) {
                    str = ((MessageModel) t).getMessage();
                } else if (t instanceof Deal) {
                    str = ((Deal) t).getAdvert().getTittle();
                } else {
                    return null;
                }

                if (str.toLowerCase().contains(search.toLowerCase())) {
                    newList.add(t);
                }
            }
            return newList;
        }
        return list;
    }

}
