package vsu.netcracker.project.subModels;

import vsu.netcracker.project.database.models.Ingredient;

public class IngredientForTomorrow extends Ingredient {

    private double quantityIngredientsForTomorrow;
    private Ingredient ingredient;

    public IngredientForTomorrow() {
    }

    public IngredientForTomorrow(Ingredient ingredient, double quantityIngredientsForTomorrow) {
        this.quantityIngredientsForTomorrow = quantityIngredientsForTomorrow;
        this.ingredient = ingredient;
    }

    public double getQuantityIngredientsForTomorrow() {
        return quantityIngredientsForTomorrow;
    }

    public void setQuantityIngredientsForTomorrow(double quantityIngredientsForTomorrow) {
        this.quantityIngredientsForTomorrow = quantityIngredientsForTomorrow;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public boolean containsIngredient(Ingredient ingredient) {
        return this.ingredient == ingredient;
    }

}
