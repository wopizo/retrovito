package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByMessage(String type);
}
