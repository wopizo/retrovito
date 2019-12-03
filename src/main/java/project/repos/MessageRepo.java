package project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.domain.Message;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByMessage(String type);
}
