package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Dishes")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_id")
    private Long id;
    @Column(name = "name", nullable = false) // есть еще @NotNull
    private String name;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "ingredient", nullable = false)
    private String ingredient;
    @Column(name = "recipe", nullable = false)
    private String recipe;
    @Column(name = "mass", nullable = false)
    private String mass;
    @Column(name = "preparingTime", nullable = false)
    private Time preparingTime;
    @Column(name = "img_url", nullable = false)
    private String imgUrl;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "type_dish_id")
    private TypeDish typeDish;
    @OneToMany
    @JsonBackReference
    @JoinColumn(name = "dishes_id", insertable = false, updatable = false)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dishes dishes = (Dishes) o;
        return getId().equals(dishes.getId()) &&
                getName().equals(dishes.getName()) &&
                getPrice().equals(dishes.getPrice()) &&
                getIngredient().equals(dishes.getIngredient()) &&
                getRecipe().equals(dishes.getRecipe()) &&
                getMass().equals(dishes.getMass()) &&
                getPreparingTime().equals(dishes.getPreparingTime()) &&
                getImgUrl().equals(dishes.getImgUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getIngredient(), getRecipe(), getMass(), getPreparingTime(), getImgUrl());
    }
}
