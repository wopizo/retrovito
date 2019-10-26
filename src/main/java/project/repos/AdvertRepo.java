package project.repos;

import org.springframework.data.repository.CrudRepository;
import project.domain.Advert;

import java.util.List;

public interface AdvertRepo extends CrudRepository<Advert, Long> {

    List<Advert> findByType(String type);

}