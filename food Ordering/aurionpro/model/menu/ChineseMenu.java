package com.aurionpro.model.menu;


import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.food.IFoodItem;

	public class ChineseMenu implements IMenu {
	    private List<IFoodItem> items;

	    public ChineseMenu() {
	        items = new ArrayList<>();
	    }

	    public void addItem(IFoodItem item) {
	        items.add(item);
	    }

	    public void removeItem(IFoodItem item) {
	        items.remove(item);
	    }

	    public List<IFoodItem> getItems() {
	        return items;
	    }
}


