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
@Table(name="studio")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Studio {
    @Id
    @Column(name="studio_id")
    private @Getter int studio_id;
    @Column(name="studioname")
    private @Getter String studioname;
}
