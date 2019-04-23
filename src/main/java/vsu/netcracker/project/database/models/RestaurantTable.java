package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
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
import java.util.List;

// ToDo - добавить еще необходимых атрибутов

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "restaurant_table")
@Data
@ToString(exclude = { "ordersList", "tableStatus" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"ordersList", "tableStatus"})
public class RestaurantTable implements Serializable {

    private static final long serialVersionUID = 4L;

    /**
     * id of the {@link RestaurantTable} (although this is a tableNumber)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_table_id")
    private Integer id;
    @OneToMany(mappedBy = "restaurantTable")
    @JsonManagedReference
    private List<Order> ordersList;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "table_status_id")
    private TableStatus tableStatus;
}
