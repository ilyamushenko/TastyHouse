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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "restaurant_order")
@Data
@ToString(exclude = { "typePayment", "dishesFromOrder", "orderStatus", "restaurantTable" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"type", "dateOrders", "typePayment", "dishesFromOrder", "orderStatus", "restaurantTable"})
public class Order implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "date_orders", nullable = false)
    private Timestamp dateOrders;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "type_payment_id")
    private TypePayment typePayment;
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<DishesFromOrder> dishesFromOrder;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "restaurant_table_id")
    private RestaurantTable restaurantTable;

    public Order(String type, Timestamp dateOrders, List<DishesFromOrder> dishesFromOrder, OrderStatus orderStatus) {
        this.type = type;
        this.dateOrders = dateOrders;
        this.dishesFromOrder = dishesFromOrder;
        this.orderStatus = orderStatus;
    }
}
