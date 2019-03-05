package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// ToDo - добавить еще необходимых атрибутов

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable implements Serializable {

    private static final long serialVersionUID = 4L;

    /**
     * id of the {@link RestaurantTable} (although this is a tableNumber)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_table_id")
    private Integer id; // служит и как id, и как номер столика
    @OneToMany(mappedBy = "restaurantTable")
    @JsonManagedReference
    private List<Order> ordersList;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "table_status_id")
    private TableStatus tableStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
}
