package com.example;

import java.time.LocalDateTime;

/**
 * Created by jessicatracy on 2/3/17.
 */
public class Transaction {
    private String date;
    private double amount;
    private String name;
    private TransactionType type;
    private TransactionMedium medium;
    private TransactionCategory category;

    public Transaction() {
    }

    public Transaction(String date, String name, double amount, TransactionType type, TransactionMedium medium, TransactionCategory category) {
        this.date = date;
        this.amount = amount;
        this.name = name;
        this.type = type;
        this.medium = medium;
        this.category = category;
    }

    //Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionMedium getMedium() {
        return medium;
    }

    public void setMedium(TransactionMedium medium) {
        this.medium = medium;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }
}

