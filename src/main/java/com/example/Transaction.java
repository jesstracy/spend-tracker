package com.example;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by jessicatracy on 2/3/17.
 */
@Table(name = "transactions")
public class Transaction {
    @GeneratedValue
    @Id
    private int id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TransactionType type;

    @Column
    private TransactionMedium medium;

    @Column
    private TransactionCategory category;

    public Transaction() {
    }

    public Transaction(String date, String name, double amount, String type, String medium, String category) {
        this.date = date;
        this.amount = amount;
        this.name = name;
        this.type = convertType(type);
        this.medium = convertMedium(medium);
        this.category = convertCategory(category);
    }

    //Getters and setters
    public int getId() {
        return id;
    }

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

    //Conversion methods
    private TransactionType convertType(String type) {
        if (type.equals("Withdrawal")) {
            return TransactionType.WITHDRAWAL;
        } else if (type.equals("Deposit")) {
            return TransactionType.DEPOSIT;
        } else {
            return null;
        }
    }

    private TransactionMedium convertMedium(String medium) {
        if (medium.equals("Credit card")) {
            return TransactionMedium.CREDIT_CARD;
        } else if (medium.equals("Debit card")) {
            return TransactionMedium.DEBIT_CARD;
        } else if (medium.equals("Cash")) {
            return TransactionMedium.CASH;
        } else if (medium.equals("Check")) {
            return TransactionMedium.CHECK;
        } else {
            return null;
        }
    }

    private TransactionCategory convertCategory(String category) {
        if (category.equals("Grocery")) {
            return TransactionCategory.GROCERY;
        } else if (category.equals("Gas")) {
            return TransactionCategory.GAS;
        } else if (category.equals("Restaurant")) {
            return TransactionCategory.RESTAURANT;
        } else if (category.equals("Entertainment")) {
            return TransactionCategory.ENTERTAINMENT;
        } else if (category.equals("Monthly bill")) {
            return TransactionCategory.MONTHLY_BILL;
        } else if (category.equals("Other")) {
            return TransactionCategory.OTHER;
        } else {
            return null;
        }
    }
}

