package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type", nullable = false)
    private String type;
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ???
    @Column(name = "tableNumber", nullable = false)
    private Long tableNumber;
    @Column(name = "dateOrders", nullable = false)
    private Timestamp dateOrders;
    @ManyToOne
    private DishesFromOrder dishesFromOrder;
    @ManyToOne
    private Statuses statuses;
    @ManyToOne
    private Staff staff;

    public Orders() {

    }

    public Orders(String type, Long tableNumber, Timestamp dateOrders, DishesFromOrder dishesFromOrder, Statuses statuses) {
        this.type = type;
        this.tableNumber = tableNumber;
        this.dateOrders = dateOrders;
        this.dishesFromOrder = dishesFromOrder;
        this.statuses = statuses;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Long tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Timestamp getDateOrders() {
        return dateOrders;
    }

    public void setDateOrders(Timestamp dateOrders) {
        this.dateOrders = dateOrders;
    }

    public DishesFromOrder getDishesFromOrder() {
        return dishesFromOrder;
    }

    public void setDishesFromOrder(DishesFromOrder dishesFromOrder) {
        this.dishesFromOrder = dishesFromOrder;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return getId().equals(orders.getId()) &&
                getType().equals(orders.getType()) &&
                getTableNumber().equals(orders.getTableNumber()) &&
                getDateOrders().equals(orders.getDateOrders()) &&
                getDishesFromOrder().equals(orders.getDishesFromOrder()) &&
                getStatuses().equals(orders.getStatuses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getTableNumber(), getDateOrders(), getDishesFromOrder(), getStatuses());
    }
}
