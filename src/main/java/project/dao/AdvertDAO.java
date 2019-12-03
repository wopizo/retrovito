package project.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.domain.Advert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class AdvertDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public List<Advert> filterAndSort(Long froom, Long too, String type, String company, String sort) {
        String query = "SELECT * FROM advert " +
                "WHERE IF ( :froom IS NOT NULL, cost > :froom, TRUE ) " +
                "AND IF ( :too IS NOT NULL, cost < :too, TRUE )" +
                "AND IF ( :type IS NOT NULL, type = :type, TRUE )" +
                "AND IF ( :company IS NOT NULL, company = :company, TRUE )" +
                "ORDER BY " + sort;
        return em.createNativeQuery(query, Advert.class)
                .setParameter("froom", froom)
                .setParameter("too", too)
                .setParameter("type", type)
                .setParameter("company", company)
                .getResultList();
    }
}
