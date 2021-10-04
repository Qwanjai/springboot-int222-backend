package int222.backend.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="UserAuthority",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "user_id"),inverseJoinColumns = @JoinColumn(name="authority_id",referencedColumnName = "authority_id"))
    private Set<Authority> roles = new HashSet<>();

    public void setRole(Authority role){
        this.roles.add(role);
    }
}
