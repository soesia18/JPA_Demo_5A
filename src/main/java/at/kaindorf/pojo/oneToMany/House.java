package at.kaindorf.pojo.oneToMany;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class House {
    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private int levels;
    private double householdIncome;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resident> residents = new ArrayList<>();

    public void addResident (Resident resident) {
        residents.add(resident);
        resident.setHouse(this);
        householdIncome += resident.getIncome();
    }

    public void removeResident (Resident resident) {
        residents.remove(resident);
        householdIncome -= resident.getIncome();
    }
}
