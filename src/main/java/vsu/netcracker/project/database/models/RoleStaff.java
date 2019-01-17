package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "RoleStaff")
public class RoleStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Staff> persons = new HashSet<Staff>(); // ToDo - может другой контейнер???

    public RoleStaff() {

    }

    public RoleStaff(String title, Set<Staff> persons) {
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

    public Set<Staff> getPersons() {
        return persons;
    }

    public void setPersons(Set<Staff> persons) {
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
