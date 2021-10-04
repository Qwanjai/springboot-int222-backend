package int222.backend.repositories;

import int222.backend.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
//    User findById(Long id);
}
