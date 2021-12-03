package int222.backend.repositories;

import int222.backend.models.entities.Genre;
import int222.backend.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}

