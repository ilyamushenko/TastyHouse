package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "dishes")
public class Dishes implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_id")
    private Integer id;
    @Column(name = "name", nullable = false) // есть еще @NotNull
    private String name;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "ingredient", nullable = false, columnDefinition = "text")
    private String ingredient;
    @Column(name = "recipe", nullable = false, columnDefinition = "text")
    private String recipe;
    @Column(name = "mass", nullable = false)
    private String mass;
    @Column(name = "preparing_time", nullable = false)
    private Time preparingTime;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "type_dish_id")
    private TypeDish typeDish;
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<DishesFromOrder> dishesFromOrder;

    public Dishes() {

    }

    public Dishes(String name, Float price, String ingredient, String recipe, String mass, Time preparingTime, TypeDish typeDish, List<DishesFromOrder> dishesFromOrder) {
        this.name = name;
        this.price = price;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.mass = mass;
        this.preparingTime = preparingTime;
        this.typeDish = typeDish;
        this.dishesFromOrder = dishesFromOrder;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public Time getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(Time preparingTime) {
        this.preparingTime = preparingTime;
    }

    public TypeDish getTypeDish() {
        return typeDish;
    }

    public void setTypeDish(TypeDish typeDish) {
        this.typeDish = typeDish;
    }

    public List<DishesFromOrder> getDishesFromOrder() {
        return dishesFromOrder;
    }

    public void setDishesFromOrder(List<DishesFromOrder> dishesFromOrder) {
        this.dishesFromOrder = dishesFromOrder;
    }
}
