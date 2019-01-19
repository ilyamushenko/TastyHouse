package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "RoleStaff")
public class RoleStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_staff_id")
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    @OneToMany
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
                getTitle().equals(roleStaff.getTitle()) &&
                getPersons().equals(roleStaff.getPersons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPersons());
    }
}
