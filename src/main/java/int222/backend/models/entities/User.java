package int222.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="user_id" )
@ToString
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
@Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="firstname",nullable = false)
    private String firstname;
    @Column(name="lastname",nullable = false)
    private String lastname;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="userauthority",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name="authority_id",referencedColumnName = "authority_id"))
    private Set<Authority> roles = new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<Comment> comments;



    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="favoritemovie",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name="movie_id",referencedColumnName = "movie_id"))
    private Set<Movie> userFav = new HashSet<>();


//    public void setRole(Set<Authority> role){
//        this.roles = role;
////        this.roles.add(role);
//    }

    public void setUserFav(Set<Movie> userFav) {
        this.userFav = userFav;
    }


    public void removeUserFav(int movie_id){
        this.userFav.remove(movie_id);
    }

    public void setFav(Movie m){
        this.userFav.add(m);
    }

    public void setRole(Authority role){
        this.roles.add(role);
    }

    public Set<Movie> getUserFav() {
        return userFav;
    }
}
