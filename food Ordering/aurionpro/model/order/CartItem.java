package com.aurionpro.model.order;

import com.aurionpro.model.food.FoodItem;

public class CartItem {
    private FoodItem foodItem;
    private int quantity;

    public CartItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public FoodItem getFoodItem() { 
    	return foodItem; 
    }
    public int getQuantity() {
    	return quantity; 
    }
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
}
