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

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE UPPER(u.username) = UPPER(?1)")
    boolean existsByUserName(String userName);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE UPPER(CONCAT(u.firstname,u.lastname) ) = UPPER(?1)")
    boolean existsByName(String fullname);
}
