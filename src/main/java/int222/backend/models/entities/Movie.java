package int222.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {
    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;
    @Column(name="moviename",nullable = false,unique = true)
    private String moviename;
    @Column(name="runtime",nullable = false)
    private double runtime;
    @Column(name="releasedate",nullable = false)
    private Date releasedate;
    @Column(name="plot",nullable = false)
    private String plot;
    @Column(name="avg_rating",nullable = false)
    private float avg_rating ;
    @Column(name="poster",nullable = false)
    private String poster;
    @Column(name="trailer",nullable = false)
    private String trailer;

    @ManyToOne
    @JoinColumn(name = "studio_id",nullable = false)
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "status_id",nullable = false)
    private Status status;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="moviegenre",
            joinColumns = @JoinColumn(name="movie_id",referencedColumnName = "movie_id"),inverseJoinColumns = @JoinColumn(name="genre_id",referencedColumnName = "genre_id"))
    private Set<Genre> movieGenre = new HashSet<>();

    @OneToMany(mappedBy="movie")
    private Set<Comment> comments;
    public void setMovieGenre(Set<Genre> movieGenre) {
        this.movieGenre = movieGenre;
    }

    public void removeComment(int movie_id){
        this.comments.remove(movie_id);
    }

}
