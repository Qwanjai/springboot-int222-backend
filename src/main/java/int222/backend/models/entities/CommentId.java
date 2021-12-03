package int222.backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CommentId  implements Serializable {
    @Column(name = "movie_id")
    private int movie_id;
    @Column(name = "user_id")
    private long user_id;
}
