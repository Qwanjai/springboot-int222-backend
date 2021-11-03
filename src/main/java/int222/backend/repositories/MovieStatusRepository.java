package int222.backend.repositories;

import int222.backend.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStatusRepository extends JpaRepository<Status,Integer> {
}
