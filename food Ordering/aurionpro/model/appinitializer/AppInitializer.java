package com.aurionpro.model.appinitializer;

import java.util.Arrays;
import java.util.List;
import com.aurionpro.model.menu.Menu;
import com.aurionpro.model.food.FoodItem;

public class AppInitializer {
    public static Menu menu;

    static {
        menu = new Menu();

        List<FoodItem> foodItems = Arrays.asList(
            // Indian Starters
            new FoodItem(1, "Paneer Tikka", 150, "Starter", "Indian"),
            new FoodItem(2, "Veg Cutlet", 120, "Starter", "Indian"),
            new FoodItem(3, "Butter Chicken", 250, "MainCourse", "Indian"),
            new FoodItem(4, "Dal Makhani", 200, "MainCourse", "Indian"),
            new FoodItem(5, "Gulab Jamun", 50, "Dessert", "Indian"),            
            new FoodItem(7, "Spring Roll", 120, "Starter", "Chinese"),
            new FoodItem(8, "Chicken crispy", 220, "Starter", "Chinese"),
            new FoodItem(9, "Noodles", 180, "MainCourse", "Chinese"),  
            new FoodItem(10, "Fried Rice", 220, "MainCourse", "Chinese"),
            new FoodItem(11, "Honey Noodles with Ice Cream", 90, "Dessert", "Chinese")
            );

        for (FoodItem foodItem : foodItems) {
            menu.addItem(foodItem);
        }
    }

    public static Menu getMenu() {
        return menu;
    }
}
