package com.aurionpro.model.menu;

import java.util.ArrayList;
import java.util.List;
import com.aurionpro.model.food.FoodItem;

public class Menu {
    private List<FoodItem> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public FoodItem getItemById(int id) {
        for (FoodItem item : items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    public boolean removeItem(int id, String cuisine) {
        for (FoodItem item : items) {
            if (item.getId() == id && item.getCuisine().equalsIgnoreCase(cuisine)) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }


}
