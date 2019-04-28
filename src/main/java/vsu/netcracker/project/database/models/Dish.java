package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import vsu.netcracker.project.database.models.enums.StatusDish;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "dish")
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
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    //@ColumnDefault("available")
    private StatusDish statusDish;
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

    public Dish() {

    }

    public Dish(String name, Float price, String recipe, String mass, Time preparingTime, TypeDish typeDish, List<DishesFromOrder> dishesFromOrder, List<FoodIngredients> foodIngredients, StatusDish statusDish) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.mass = mass;
        this.preparingTime = preparingTime;
        this.typeDish = typeDish;
        this.dishesFromOrder = dishesFromOrder;
        this.ingredients = foodIngredients;
        this.statusDish = statusDish;
    }
    public Dish(String name, Float price, String recipe, String mass, Time preparingTime, TypeDish typeDish, String imgUrl, String description, StatusDish statusDish) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.mass = mass;
        this.preparingTime = preparingTime;
        this.typeDish = typeDish;
        this.imgUrl = imgUrl;
        this.description = description;
        this.statusDish = statusDish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
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

    public List<FoodIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<FoodIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public StatusDish getStatusDish() {
        return statusDish;
    }

    public void setStatusDish(StatusDish statusDish) {
        this.statusDish = statusDish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id.equals(dish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", recipe='" + recipe + '\'' +
                ", mass='" + mass + '\'' +
                ", preparingTime=" + preparingTime +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", statusDish=" + statusDish +
                ", typeDish=" + typeDish +
                ", dishesFromOrder=" + dishesFromOrder +
                ", staffList=" + staffList +
                ", ingredients=" + ingredients +
                '}';
    }
}
