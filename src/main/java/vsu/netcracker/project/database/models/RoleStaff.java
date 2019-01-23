package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RoleStaff")
public class RoleStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_staff_id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany
    @JsonBackReference
    @JoinColumn(name = "role_staff_id", updatable = false, insertable = false)
    private List<Staff> persons;

    public RoleStaff() {

    }

    public RoleStaff(String title, List<Staff> persons) {
        this.title = title;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
