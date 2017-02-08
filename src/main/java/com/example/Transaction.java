package com.example;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by jessicatracy on 2/3/17.
 */
@Entity
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

    public Transaction(String date, String name, double amount, TransactionType type, TransactionMedium medium, TransactionCategory category) {
        this.date = date;
        this.amount = amount;
        this.name = name;
        this.type = type;
        this.medium = medium;
        this.category = category;
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

}

