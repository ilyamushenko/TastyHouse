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
@Table(name = "type_dish")
@Data
@ToString(exclude = { "dishes" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"title", "dishes"})
public class TypeDish implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_dish_id")
    private Integer id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @OneToMany(mappedBy = "typeDish")
    @JsonBackReference
    private List<Dish> dishes;

    public TypeDish(String title, List<Dish> dishes) {
        this.title = title;
        this.dishes = dishes;
    }
}
