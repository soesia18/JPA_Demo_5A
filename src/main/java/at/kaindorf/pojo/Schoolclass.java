package at.kaindorf.pojo;
//plain old java package

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Schoolclass {
    @Id
    private String initials;
    private String title;
    private String lastname;
    private String firstname;
    private String classname;
    private int studentAmount;
    private String roomName;
}
