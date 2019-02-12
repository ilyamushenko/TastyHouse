package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

// ToDo - я тут подумал: надо применить аннотацию @Formula для расчета процента для прогесс бара у официанта (ну попробовать по крайней мере)

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
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "orders_id")
    private Orders order;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "dishes_id")
    private Dishes dish;

    public DishesFromOrder() {

    }

    public DishesFromOrder(Time realTime, String status) {
        this.realTime = realTime;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
