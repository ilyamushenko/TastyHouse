package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "dish")
@Data
@ToString(exclude = { "typeDish", "dishesFromOrder", "staffList", "ingredients" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"name", "price", "recipe", "mass", "preparingTime",
        "imgUrl", "description", "stopList", "typeDish", "dishesFromOrder", "staffList", "ingredients"})
public class Dish implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Integer id;
    @Column(name = "name", nullable = false) // есть еще @NotNull
    private String name;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "recipe", nullable = false, columnDefinition = "text")
    private String recipe;
    @Column(name = "mass", nullable = false)
    private String mass;
    @Column(name = "preparing_time", nullable = false)
    private Time preparingTime;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "stopList")
    private Boolean stopList;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "type_dish_id")
    private TypeDish typeDish;
    @OneToMany(mappedBy = "dish")
    @JsonBackReference
    private List<DishesFromOrder> dishesFromOrder;
    @ManyToMany
    @JoinTable(
            name = "dishes_and_staff",
            joinColumns = {@JoinColumn(name = "dish_id")},
            inverseJoinColumns = {@JoinColumn(name = "staff_id")}
    )
    @JsonManagedReference
    private List<Staff> staffList;

    @OneToMany(mappedBy = "dish")
    @JsonManagedReference
    private List<FoodIngredients> ingredients;

    public Dish(String name, Float price, String recipe, String mass, Time preparingTime, TypeDish typeDish, List<DishesFromOrder> dishesFromOrder, List<FoodIngredients> foodIngredients, Boolean stopList) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.mass = mass;
        this.preparingTime = preparingTime;
        this.typeDish = typeDish;
        this.dishesFromOrder = dishesFromOrder;
        this.ingredients = foodIngredients;
        this.stopList = stopList;
    }

    public Dish(String name, Float price, String recipe, String mass, Time preparingTime, TypeDish typeDish, String imgUrl, String description, Boolean stopList) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.mass = mass;
        this.preparingTime = preparingTime;
        this.typeDish = typeDish;
        this.imgUrl = imgUrl;
        this.description = description;
        this.stopList = stopList;
    }
}
