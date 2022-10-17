package at.kaindorf.pojo.oneToMany;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"house"})
@EqualsAndHashCode(exclude = {"house"})
public class Resident {
    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private String name;
    @NonNull
    private double income;

    @ManyToOne
    private House house;
}
