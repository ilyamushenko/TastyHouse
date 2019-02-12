package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "statuses")
public class Statuses implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statuses_id")
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToMany(mappedBy = "statuses", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Orders> orders;

    public Statuses() {

    }

    public Statuses(String title) {
        this.title = title;
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statuses statuses = (Statuses) o;
        return getId().equals(statuses.getId()) &&
                getTitle().equals(statuses.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public String toString() {
        return "Statuses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", orders=" + orders +
                '}';
    }
}
