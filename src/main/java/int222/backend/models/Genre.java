package int222.backend.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Genre {
    @Id
    private @Getter  int genre_id;
    private @Getter String genre;

}
