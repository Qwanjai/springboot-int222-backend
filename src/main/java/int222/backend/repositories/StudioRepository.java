package int222.backend.repositories;

import int222.backend.models.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Integer> {
}
