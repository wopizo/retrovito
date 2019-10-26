package project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import project.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
