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

    @OneToOne(cascade = CascadeType.PERSIST)
    private Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
        driver.setCar(this);
    }
}
