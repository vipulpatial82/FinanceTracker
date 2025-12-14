package com.finance.tracker;

public class Transaction {
    private double amount;
    private String category;
    private String description;
    private boolean isIncome;

    public Transaction(double amount, String category, String description, boolean isIncome) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.isIncome = isIncome;
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public boolean isIncome() { return isIncome; }
}
