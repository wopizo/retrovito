package project.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Message;
import project.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class MessageDAO{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public List<Message> getAll() {
        return em.createNativeQuery("SELECT * FROM message WHERE id = :id", Message.class)
                .setParameter("id", 1)
                .getResultList();
    }

    public List<Message> getThisToUser(User from, User to) {
        return em.createNativeQuery(
                "SELECT * FROM message WHERE user_from = :idFrom AND user_to = :idTo OR user_from = :idTo AND user_to = :idFrom ORDER BY date",
                Message.class)
                .setParameter("idFrom", from.getId())
                .setParameter("idTo", to.getId())
                .getResultList();
    }

    public List<Message> getUserChats(User from) {
        String query = "SELECT m.* FROM " +
                "( SELECT MAX(id) AS 'maxid', IF (user_from = :idFrom, user_to, user_from) AS 'user' " +
                "FROM message " +
                "WHERE user_from = :idFrom OR user_to = :idFrom GROUP BY user ) AS a " +
                "INNER JOIN message AS m ON a.maxid = m.id " +
                "ORDER BY m.id DESC";
        return em.createNativeQuery(query, Message.class)
                .setParameter("idFrom", from.getId())
                .getResultList();
    }
}
