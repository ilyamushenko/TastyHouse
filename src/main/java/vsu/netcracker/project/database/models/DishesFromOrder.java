package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;

@Entity
@Table(name = "DishesFromOrder")
public class DishesFromOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_from_order_id")
    private Long id;
    @Column(name = "realTime")
    private Time realTime;
    @Column(name = "status", nullable = false)
    private String status;
    @OneToMany
    @JoinColumn(name = "dishes_from_order_id", insertable = false, updatable = false)
    private List<Orders> ordersSet;
    @OneToMany
    @JoinColumn(name = "dishes_from_order_id", insertable = false, updatable = false)
    private List<Dishes> dishesSet;

    public DishesFromOrder() {

    }

    public DishesFromOrder(Time realTime, String status, List<Orders> ordersSet, List<Dishes> dishesSet) {
        this.realTime = realTime;
        this.status = status;
        this.ordersSet = ordersSet;
        this.dishesSet = dishesSet;
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

    public List<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(List<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    public List<Dishes> getDishesSet() {
        return dishesSet;
    }

    public void setDishesSet(List<Dishes> dishesSet) {
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
}
