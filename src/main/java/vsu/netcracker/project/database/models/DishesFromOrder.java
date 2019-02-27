package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "dishes_from_order")
public class DishesFromOrder implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_from_order_id")
    private Integer id;
    @Column(name = "real_time")
    private Time realTime;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "dish_status_id")
    private DishStatus dishStatus;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "orders_id")
    private Orders order;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "dishes_id")
    private Dishes dish;

    public DishesFromOrder() {

    }

    public DishesFromOrder(Time realTime, DishStatus dishStatus) {
        this.realTime = realTime;
        this.dishStatus = dishStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getRealTime() {
        return realTime;
    }

    public void setRealTime(Time realTime) {
        this.realTime = realTime;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Dishes getDish() {
        return dish;
    }

    public void setDish(Dishes dish) {
        this.dish = dish;
    }

    public DishStatus getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(DishStatus dishStatus) {
        this.dishStatus = dishStatus;
    }
}
