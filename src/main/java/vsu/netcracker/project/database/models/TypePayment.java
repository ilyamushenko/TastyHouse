package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "type_payment")
@Data
@ToString(exclude = { "orders" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"title", "orders"})
public class TypePayment implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_payment_id")
    private Integer id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @OneToMany(mappedBy = "typePayment")
    @JsonBackReference
    private List<Order> orders;

    public TypePayment(String title, List<Order> orders) {
        this.title = title;
        this.orders = orders;
    }
}
