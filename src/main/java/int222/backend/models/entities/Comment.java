package int222.backend.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @EmbeddedId
    private CommentId commentid;


    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;


    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "commenttext", nullable = true)
    private String commenttext;
    @Column(name = "create_date", nullable = false)
    private String create_date;
    @Column(name = "rating", nullable = false)
    private float rating;
    

    public void setCommentid(int movie_id, long user_id) {
        this.commentid = new CommentId(movie_id,user_id);
    }

    public Comment(int movie_id, long user_id, Movie movie, User user, String commenttext, String create_date, float rating) {
        this.commentid = new CommentId(movie_id,user_id);
        this.movie = movie;
        this.user = user;
        this.commenttext = commenttext;
        this.create_date = create_date;
        this.rating = rating;
    }

    public String getUsername() {
        return user.getUsername();
    }

}

