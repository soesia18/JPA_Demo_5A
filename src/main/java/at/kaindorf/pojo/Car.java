package at.kaindorf.pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private String brand;
    @NonNull
    private int hp;

    @OneToOne
    @ToString.Exclude
    private Driver driver;
}
