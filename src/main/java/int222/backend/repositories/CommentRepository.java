package int222.backend.repositories;

import int222.backend.models.entities.Comment;
import int222.backend.models.entities.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {


}
