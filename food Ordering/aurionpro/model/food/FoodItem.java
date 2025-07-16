package com.aurionpro.model.food;

public class FoodItem {
    private int id;
    private String name;
    private double price;
    private String category;
    private String cuisine; 

    public FoodItem(int id, String name, double price, String category, String cuisine) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.cuisine = cuisine;
    }

    public int getId() { 
    	return id; 
    }
    
    public String getName() { 
    	return name; 
    }
    
    public double getPrice() { 
    	return price; 
    }
    
    public String getCategory() {
    	return category; 
    }
    
    public String getCuisine() {
    	return cuisine; 
    }


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	} 
    
    
}
