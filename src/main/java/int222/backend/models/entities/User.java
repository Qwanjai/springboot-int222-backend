package int222.backend.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false, unique = true,name = "username")
    private String username;
    @Column(nullable = false,name="password")
    private String password;
    @Column(nullable = false,name="firstname")
    private String firstname;
    @Column(nullable = false,name="lastname")
    private String lastname;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="userauthority",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name="authority_id",referencedColumnName = "authority_id"))
    private Set<Authority> roles = new HashSet<>();

    public void setRole(Authority role){
        this.roles.add(role);
    }
}
