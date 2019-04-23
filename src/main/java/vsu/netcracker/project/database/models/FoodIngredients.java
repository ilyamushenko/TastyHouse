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
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Попова Алина
 */
@Entity
@Table(name = "food_ingredients")
@Data
@ToString(exclude = { "dish", "ingredient" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"dish", "ingredient", "quantity"})
public class FoodIngredients implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_ingredients_id")
    private Integer id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity", nullable = false)
    private Float quantity;
}
