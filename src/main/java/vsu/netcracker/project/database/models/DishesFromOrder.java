package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DishesFromOrder")
public class DishesFromOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "realTime")
    private Time realTime;
    @Column(name = "status", nullable = false)
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Orders> ordersSet = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Dishes> dishesSet = new HashSet<>();

    public DishesFromOrder() {

    }

    public DishesFromOrder(Time realTime, String status, Set<Orders> ordersSet) {
        this.realTime = realTime;
        this.status = status;
        this.ordersSet = ordersSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getRealTime() {
        return realTime;
    }

    public void setRealTime(Time realTime) {
        this.realTime = realTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    public Set<Dishes> getDishesSet() {
        return dishesSet;
    }

    public void setDishesSet(Set<Dishes> dishesSet) {
        this.dishesSet = dishesSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishesFromOrder that = (DishesFromOrder) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getRealTime(), that.getRealTime()) &&
                getStatus().equals(that.getStatus()) &&
                getOrdersSet().equals(that.getOrdersSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRealTime(), getStatus(), getOrdersSet());
    }
}
