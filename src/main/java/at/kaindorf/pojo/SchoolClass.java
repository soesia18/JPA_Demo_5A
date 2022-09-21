package at.kaindorf.pojo;
//plain old java package

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@NamedQueries({
        @NamedQuery(
                name = "SchoolClass.findByStudentAmount",
                query = "SELECT sc FROM SchoolClass sc WHERE sc.studentAmount > :studentAmount ORDER BY sc.studentAmount"
        )
})
public class SchoolClass {

    @Id
    @GeneratedValue
    @Column(name = "schoolclass_id")
    private int id;
    @NonNull
    @Column(length = 2, nullable = false)
    private String initials;
    @NonNull
    private String title;
    @NonNull
    private String lastname;
    @NonNull
    private String firstname;
    @NonNull
    private String classname;
    @NonNull
    private int studentAmount;
    @NonNull
    private String roomName;

    public SchoolClass (String line) {
        String[] lineData = line.split(";");

        this.initials = lineData[0];
        this.title = lineData[1];
        this.lastname = lineData[2];
        this.firstname = lineData[3];
        this.classname = lineData[4];
        this.studentAmount = Integer.parseInt(lineData[5]);
        this.roomName = lineData[6];
    }
}
