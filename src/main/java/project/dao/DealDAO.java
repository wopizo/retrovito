package project.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Deal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class DealDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public List<Deal> getAllByAuthor(Long author) {
        String query = "SELECT d2.* FROM " +
                "(SELECT dd.id AS 'id' " +
                "FROM deal as dd " +
                "INNER JOIN advert as aa ON aa.id = dd.advert " +
                "WHERE dd.advert = aa.id AND aa.author = :id) AS d " +
                "INNER JOIN deal AS d2 ON d.id = d2.id " +
                "ORDER BY d2.date DESC";
        return em.createNativeQuery(query, Deal.class)
                .setParameter("id", author)
                .getResultList();
    }

    public List<Deal> getAllByBuyer(Long buyer) {
        String query = "SELECT d2.* FROM " +
                "(SELECT dd.id AS 'id' " +
                "FROM deal as dd " +
                "INNER JOIN advert as aa ON aa.id = dd.advert " +
                "WHERE dd.advert = aa.id AND aa.buyer = :id) AS d " +
                "INNER JOIN deal AS d2 ON d.id = d2.id " +
                "ORDER BY d2.date DESC";
        return em.createNativeQuery(query, Deal.class)
                .setParameter("id", buyer)
                .getResultList();
    }

}
