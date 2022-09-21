package at.kaindorf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentPK implements Serializable {
    private String abbr;
    private int dptNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentPK that = (DepartmentPK) o;
        return dptNo == that.dptNo && Objects.equals(abbr, that.abbr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbr, dptNo);
    }
}
