package project.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class AdminDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public List<Advert> getAdverts(String from, String to){
        String query =
                "SELECT * FROM advert WHERE " +
                        "IF(:from IS NOT NULL, date >= :from, TRUE) " +
                        "AND IF(:to IS NOT NULL, date <= :to, TRUE) " +
                        "ORDER BY date DESC";

        return em.createNativeQuery(query, Advert.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public List<User> getUsers(String from, String to){
        String query =
                "SELECT * FROM user WHERE " +
                        "IF(:from IS NOT NULL, date >= :from, TRUE) " +
                        "AND IF(:to IS NOT NULL, date <= :to, TRUE) " +
                        "AND id > 0 " +
                        "AND active " +
                        "ORDER BY date";

        return em.createNativeQuery(query, User.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public List<Comment> getComments(String from, String to){
        String query =
                "SELECT * FROM comment WHERE " +
                        "IF(:from IS NOT NULL, date >= :from, TRUE) " +
                        "AND IF(:to IS NOT NULL, date <= :to, TRUE) " +
                        "ORDER BY date DESC";

        return em.createNativeQuery(query, Comment.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public List<Review> getReviews(String from, String to){
        String query =
                "SELECT * FROM review WHERE " +
                        "IF(:from IS NOT NULL, date >= :from, TRUE) " +
                        "AND IF(:to IS NOT NULL, date <= :to, TRUE) " +
                        "ORDER BY date DESC";

        return em.createNativeQuery(query, Review.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    public List<Deal> getDeals(String from, String to){
        String query =
                "SELECT * FROM deal WHERE " +
                        "IF(:from IS NOT NULL, date >= :from, TRUE) " +
                        "AND IF(:to IS NOT NULL, date <= :to, TRUE) " +
                        "ORDER BY date DESC";

        return em.createNativeQuery(query, Deal.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }
}
