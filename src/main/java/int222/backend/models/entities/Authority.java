package int222.backend.models.entities;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="authority")
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @Column(name="authority_id")
    private int authority_id;

    @Column(name="name")
    @Enumerated( EnumType.STRING)
    private UserRoleName name;



    @Override
    public String getAuthority() {
        return name.name();
    }



    public int getId() {
        return authority_id;
    }




}
