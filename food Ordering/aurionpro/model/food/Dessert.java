package com.aurionpro.model.food;

public class Dessert implements IFoodItem {
	private String name;
	private double price;

	public Dessert(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}
