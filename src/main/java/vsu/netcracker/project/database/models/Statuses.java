package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Statuses")
public class Statuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Orders> orders = new HashSet<Orders>(); // ToDo - может другой контейнер???

    public Statuses() {

    }

    public Statuses(String title, HashSet<Orders> orders) {
        this.title = title;
        this.orders = orders;
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

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statuses statuses = (Statuses) o;
        return getId().equals(statuses.getId()) &&
                getTitle().equals(statuses.getTitle()) &&
                getOrders().equals(statuses.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getOrders());
    }
}
