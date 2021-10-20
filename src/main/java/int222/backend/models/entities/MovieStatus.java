package int222.backend.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="moviestatus")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class MovieStatus {
    @Id
    @Column(name="status_id")
    private @Getter int status_id;
    @Column
    private  @Getter String statusname;
}
