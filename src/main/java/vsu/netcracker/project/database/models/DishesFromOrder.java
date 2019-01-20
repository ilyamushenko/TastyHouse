package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "dishes_from_order_id", insertable = false, updatable = false)
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "dishes_from_order_id", insertable = false, updatable = false)
    private Dishes dish;

    public DishesFromOrder() {

    }

    public DishesFromOrder(Time realTime, String status, Orders order, Dishes dish) {
        this.realTime = realTime;
        this.status = status;
        this.order = order;
        this.dish = dish;
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

    public Orders getOrdersSet() {
        return order;
    }

    public void setOrdersSet(Orders order) {
        this.order = order;
    }

    public Dishes getDishesSet() {
        return dish;
    }

    public void setDishesSet(Dishes dish) {
        this.dish = dish;
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
