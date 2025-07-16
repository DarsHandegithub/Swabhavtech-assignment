package com.aurionpro.model.order;

import java.util.ArrayList;
import java.util.List;
import com.aurionpro.model.food.FoodItem;

public class Order {
    private List<CartItem> cartItems;
    private double total;

    public Order() {
        cartItems = new ArrayList<>();
    }

    public void addItem(FoodItem item, int qty) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getFoodItem().getId() == item.getId()) {
            	cartItem.setQuantity(cartItem.getQuantity() + qty);
                calculateTotal();
                return;
            }
        }
        cartItems.add(new CartItem(item, qty));
        calculateTotal();
    }

    public void calculateTotal() {
        total = 0;
        for (CartItem cartItem : cartItems)
            total += cartItem.getFoodItem().getPrice() * cartItem.getQuantity();
    }

    public double getTotal() { return total; }
    public List<CartItem> getCartItems() { return cartItems; }
    
    public void clearCart() {
        cartItems.clear();
    }

}
