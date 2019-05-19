package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "role_staff")
public class RoleStaff implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_staff_id")
    private Integer id;
    @Column(name = "staff_login", nullable = false, unique = true, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "staffLogin", read = "pgp_sym_decrypt(staffLogin, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String staffLogin;
    @Column(name = "title", nullable = false, unique = true)
    private String title; // зашифровать???
    @OneToMany(mappedBy = "roleStaff")
    @JsonBackReference
    private List<Staff> persons;

    public RoleStaff() {

    }

    public RoleStaff(String staffLogin, String title, List<Staff> persons) {
        this.staffLogin = staffLogin;
        this.title = title;
        this.persons = persons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Staff> getPersons() {
        return persons;
    }

    public void setPersons(List<Staff> persons) {
        this.persons = persons;
    }

    public String getStaffLogin() {
        return staffLogin;
    }

    public void setStaffLogin(String staffLogin) {
        this.staffLogin = staffLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleStaff roleStaff = (RoleStaff) o;
        return id.equals(roleStaff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RoleStaff{" +
                "id=" + id +
                ", staffLogin='" + staffLogin + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
