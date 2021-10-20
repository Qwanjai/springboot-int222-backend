package int222.backend.repositories;

import int222.backend.models.entities.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStatusRepository extends JpaRepository<MovieStatus,Integer> {
}
