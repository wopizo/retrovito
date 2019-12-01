package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Advert;

import java.util.List;

@Repository
public interface AdvertRepo extends CrudRepository<Advert, Long> {

    List<Advert> findByType(String type);

}