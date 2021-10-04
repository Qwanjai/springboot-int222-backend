package int222.backend.repositories;

import int222.backend.models.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    Authority findById(int authority_id);
}
