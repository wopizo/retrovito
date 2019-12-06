package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Advert;
import project.domain.User;

import java.util.List;

@Repository
public interface AdvertRepo extends CrudRepository<Advert, Long> {

    List<Advert> findByAuthorOrderByDateDesc(User author);

    List<Advert> findByBuyerOrderByDateDesc(User buyer);

}