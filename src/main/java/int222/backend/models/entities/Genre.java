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
@Table(name="Genre")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Genre {
    @Id
    @Column(name="genre_id")
    private @Getter  int genre_id;
    @Column(name="genre")
    private @Getter String genre;

}
