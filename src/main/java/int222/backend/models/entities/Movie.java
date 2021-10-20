package int222.backend.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="movie")
@Data
public class Movie {
    @Id
    @Column(name="movie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;
    @Column(name="moviename",nullable = false,unique = true)
    private String moviename;
    @Column(name="runtime",nullable = false)
    private double runtime;
    @Column(name="plot",nullable = false)
    private String plot;
    @Column(name="avg_rating",nullable = false)
    private float avg_rating ;
    @Column(name="poster",nullable = false)
    private String poster;
    @Column(name="trailer",nullable = false)
    private String trailer;



}
