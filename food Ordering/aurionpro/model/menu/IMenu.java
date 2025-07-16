package com.aurionpro.model.menu;

import java.util.List;

import com.aurionpro.model.food.IFoodItem;

public interface IMenu {
	void addItem(IFoodItem item);

	void removeItem(IFoodItem item);

	List<IFoodItem> getItems();
}
