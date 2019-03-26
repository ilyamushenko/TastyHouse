package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

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
    @Column(name = "begin_cooking_time")
    private Time beginCookingTime;
    @Column(name = "end_cooking_time")
    private Time endCookingTime;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "dish_status_id")
    private DishStatus dishStatus;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "dish_id")
    private Dish dish;


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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public DishStatus getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(DishStatus dishStatus) {
        this.dishStatus = dishStatus;
    }

    public Timestamp getTimeOrder() {
        return order.getDateOrders();
    }

    public Time getBeginCookingTime() {
        return beginCookingTime;
    }

    public void setBeginCookingTime(Time beginCookingTime) {
        this.beginCookingTime = beginCookingTime;
    }

    public Time getEndCookingTime() {
        return endCookingTime;
    }

    public void setEndCookingTime(Time endCookingTime) {
        this.endCookingTime = endCookingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishesFromOrder that = (DishesFromOrder) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DishesFromOrder{" +
                "id=" + id +
                ", realTime=" + realTime +
                '}';
    }
}
