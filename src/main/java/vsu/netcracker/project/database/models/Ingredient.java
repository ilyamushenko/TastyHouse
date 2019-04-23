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
 * @author Попова Алина
 */
@Entity
@Table(name = "ingredient")
@Data
@ToString(exclude = { "dishes" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"name", "type", "quantity_in_stock", "unit", "dishes"})
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "quantity_in_stock", nullable = false)
    private Float quantity_in_stock;
    @Column(name = "unit", nullable = false)
    private String unit;

    @OneToMany(mappedBy = "ingredient")
    @JsonBackReference
    private List<FoodIngredients> dishes;

    public Ingredient(String name, String type, Float quantity_in_stock, String unit) {
        this.name = name;
        this.type = type;
        this.quantity_in_stock = quantity_in_stock;
        this.unit = unit;
    }
}
