package com.aurionpro.model.discount;

public class FlatDiscount implements IDiscount {
    private String discountType;

    public FlatDiscount() {
        this.discountType = "Flat Discount";
    }

    @Override
    public double applyDiscount(double total) {
        if (total > 500) {
        	System.out.println("-----------------------------");
            System.out.println("----------------------------- /nFlat Rs. 50 discount applied.");
            return total - 50;
        }
        System.out.println("No discount applied.");
        return total;
    }

    @Override
    public String getDiscountType() {
        return discountType;
    }

    // ✅ Add this static method for utility-style call
    public static double apply(double total) {
        if (total > 500) {
            System.out.println("Flat Rs. 50 discount applied.");
            return total - 50;
        }
        System.out.println("No discount applied.");
        return total;
    }
}
