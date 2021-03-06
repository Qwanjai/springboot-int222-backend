package int222.backend.repositories;


import int222.backend.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByMovienameContainsIgnoreCase(String movieName);

    @Query("SELECT m FROM Movie m WHERE m.status.status_id = ?1")
    List<Movie> findAllByStatus(int status_id);

    @Query("SELECT m FROM Movie m WHERE m.studio.studio_id = ?1")
    List<Movie> findAllByStudio(int studio_id);


    @Query("select m from Movie m left join m.movieGenre g where g.genre_id = ?1")
    List<Movie> findByMovieGenre(int genre_id);

    @Query("select m from Movie m left join m.movieGenre g where g.genre = ?1")
    List<Movie> findByMovieGenreNameContainsIgnoreCase(String genre);


    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Movie m WHERE UPPER(m.moviename) = UPPER(?1)")
    boolean existsByName(String movieName);


    List<Movie> findTop5By();




}
