package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TypePayment")
public class TypePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_payment_id")
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    @OneToMany
    @JoinColumn(name = "type_payment_id", insertable = false, updatable = false)
    private List<Orders> orders;

    public TypePayment() {

    }

    public TypePayment(String title, List<Orders> orders) {
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
        TypePayment that = (TypePayment) o;
        return getId().equals(that.getId()) &&
                getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
