package int222.backend.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="status")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Status {
    @Id
    @Column(name="status_id")
    private @Getter int status_id;
    @Column(name="statusname")
    private  @Getter String statusname;

    @OneToMany(mappedBy="status")
    private Set<Movie> movies;
}
