package com.aurionpro.model.food;

public class MainCourse implements IFoodItem {
	private String name;
	private double price;

	public MainCourse(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
