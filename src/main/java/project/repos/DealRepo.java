package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Advert;
import project.domain.Deal;

@Repository
public interface DealRepo extends CrudRepository<Deal, Long> {
    Deal findDealByAdvert(Advert advert);
}
