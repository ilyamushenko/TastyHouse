package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role_staff")
public class RoleStaff implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_staff_id")
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(mappedBy = "roleStaff", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Staff> persons;

    public RoleStaff() {

    }

    public RoleStaff(String title, List<Staff> persons) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleStaff roleStaff = (RoleStaff) o;
        return getId().equals(roleStaff.getId()) &&
                getTitle().equals(roleStaff.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "RoleStaff{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", persons=" + persons +
                '}';
    }
}
