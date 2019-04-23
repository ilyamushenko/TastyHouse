/*
 * Project: TastyHouse
 * Class: DishNameAndPrice
 * Created: Alina Popova / MISS-CEH4TOP
 * Date: 23.04.19 1:44
 */

package vsu.netcracker.project.subModels;

import java.io.Serializable;
import java.util.Objects;

public class DishNameAndPrice implements Serializable {
    private static final long serialVersionUID = 4L;

    private String name;
    private float price;
    private float priceDerivedByIngredients;


    public DishNameAndPrice(String name, float price, float priceDerivedByIngredients) {
        this.name = name;
        this.price = price;
        this.priceDerivedByIngredients = priceDerivedByIngredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceDerivedByIngredients() {
        return priceDerivedByIngredients;
    }

    public void setPriceDerivedByIngredients(float priceDerivedByIngredients) {
        this.priceDerivedByIngredients = priceDerivedByIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishNameAndPrice that = (DishNameAndPrice) o;
        return Float.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, priceDerivedByIngredients);
    }
}
