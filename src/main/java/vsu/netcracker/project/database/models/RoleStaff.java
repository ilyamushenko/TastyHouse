package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "role_staff")
@Data
@ToString(exclude = { "persons" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"title", "persons"})
public class RoleStaff implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_staff_id")
    private Integer id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @OneToMany(mappedBy = "roleStaff")
    @JsonBackReference
    private List<Staff> persons;

    public RoleStaff(String title, List<Staff> persons) {
        this.title = title;
        this.persons = persons;
    }
}
