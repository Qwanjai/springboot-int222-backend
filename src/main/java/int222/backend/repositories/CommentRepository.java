package int222.backend.repositories;

import int222.backend.models.entities.Comment;
import int222.backend.models.entities.CommentId;
import int222.backend.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    @Query("SELECT c FROM Comment c WHERE c.commentid.movie_id = ?1")
    List<Comment> findByMovieId(int movie_id);

    @Query("SELECT c FROM Comment c WHERE c.commentid.user_id = ?1")
    List<Comment> findByUserId(long user_id);
}
