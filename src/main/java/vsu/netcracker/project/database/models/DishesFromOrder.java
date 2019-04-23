package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "dishes_from_order")
@Data
@ToString(exclude = { "dishStatus", "order", "dish" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"realTime", "beginCookingTime", "endCookingTime", "dishStatus", "order", "dish"})
public class DishesFromOrder implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_from_order_id")
    private Integer id;
    @Column(name = "real_time")
    private Time realTime;
    @Column(name = "begin_cooking_time")
    private Timestamp beginCookingTime;
    @Column(name = "end_cooking_time")
    private Timestamp endCookingTime;
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

    public DishesFromOrder(Time realTime, DishStatus dishStatus) {
        this.realTime = realTime;
        this.dishStatus = dishStatus;
    }

    public Timestamp getTimeOrder() {
        return order.getDateOrders();
    }
}
