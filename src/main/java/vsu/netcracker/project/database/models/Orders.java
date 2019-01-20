package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;
    @Column(name = "type", nullable = false)
    private String type;
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ???
    @Column(name = "tableNumber", nullable = false)
    private Long tableNumber;
    @Column(name = "dateOrders", nullable = false)
    private Timestamp dateOrders;
    @ManyToOne
    @JoinColumn(name = "type_payment_id")
    private TypePayment typePayment;
    @OneToMany
    @JoinColumn(name = "orders_id", insertable = false, updatable = false)
    private List<DishesFromOrder> dishesFromOrder;
    @ManyToOne
    @JoinColumn(name = "statuses_id")
    private Statuses statuses;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Orders() {

    }

    public Orders(String type, Long tableNumber, Timestamp dateOrders, List<DishesFromOrder> dishesFromOrder, Statuses statuses) {
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", tableNumber=" + tableNumber +
                ", dateOrders=" + dateOrders +
                ", typePayment=" + typePayment +
                ", dishesFromOrder=" + dishesFromOrder +
                ", statuses=" + statuses +
                ", staff=" + staff +
                '}';
    }
}
