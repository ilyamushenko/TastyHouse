package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Integer id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "table_number", nullable = false)
    private Integer tableNumber;
    @Column(name = "date_orders", nullable = false)
    private Timestamp dateOrders;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "type_payment_id")
    private TypePayment typePayment;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<DishesFromOrder> dishesFromOrder;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "statuses_id")
    private Statuses statuses;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Orders() {

    }

    public Orders(String type, Integer tableNumber, Timestamp dateOrders, List<DishesFromOrder> dishesFromOrder, Statuses statuses) {
        this.type = type;
        this.tableNumber = tableNumber;
        this.dateOrders = dateOrders;
        this.dishesFromOrder = dishesFromOrder;
        this.statuses = statuses;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
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
                getDateOrders().equals(orders.getDateOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getTableNumber(), getDateOrders());
    }
}
