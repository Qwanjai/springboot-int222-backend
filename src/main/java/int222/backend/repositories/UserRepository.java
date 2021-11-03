package int222.backend.repositories;

import int222.backend.models.entities.Movie;
import int222.backend.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Optional<User> findByFirstnameIgnoreCase(String firstname);

    @Query("SELECT u FROM User u JOIN FETCH u.userFav WHERE u.user_id = ?1 ")
    List<Movie> findAllByUserFav(long  user_id);
}
