package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "restaurant_order")
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

    public Order() {

    }

    public Order(String type, Timestamp dateOrders, List<DishesFromOrder> dishesFromOrder, OrderStatus orderStatus) {
        this.type = type;
        this.dateOrders = dateOrders;
        this.dishesFromOrder = dishesFromOrder;
        this.orderStatus = orderStatus;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateOrders() {
        return dateOrders;
    }

    public void setDateOrders(Timestamp dateOrders) {
        this.dateOrders = dateOrders;
    }

    public List<DishesFromOrder> getDishesFromOrder() {
        return dishesFromOrder;
    }

    public void setDishesFromOrder(List<DishesFromOrder> dishesFromOrder) {
        this.dishesFromOrder = dishesFromOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getType().equals(order.getType()) &&
                getDateOrders().equals(order.getDateOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDateOrders());
    }
}
