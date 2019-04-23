package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Попова Алина
 */
@Entity
@Table(name = "ingredient")
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
    @Column(name="price", nullable = false)
    private Float price;

    @OneToMany(mappedBy = "ingredient")
    @JsonBackReference
    private List<FoodIngredients> dishes;

    public Ingredient() {

    }
    public Ingredient(String name, String type, Float quantity_in_stock, String unit, Float price) {
        this.name = name;
        this.type = type;
        this.quantity_in_stock = quantity_in_stock;
        this.unit = unit;
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(Float quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<FoodIngredients> getDishes() {
        return dishes;
    }

    public void setDishes(List<FoodIngredients> dishes) {
        this.dishes = dishes;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quantity_in_stock=" + quantity_in_stock +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", dishes=" + dishes +
                '}';
    }
}
