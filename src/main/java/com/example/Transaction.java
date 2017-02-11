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

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String name;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private TransactionType type;
//
//    @Column
//    @Enumerated(EnumType.STRING)
//    private TransactionMedium medium;
//
//    @Column
//    @Enumerated(EnumType.STRING)
//    private TransactionCategory category;

    @Column(nullable = false)
    private String type;

    @Column
    private String medium;

    @Column
    private String category;

    public Transaction() {
    }

    public Transaction(String date, String name, double amount, String type, String medium, String category, User user) {
        this.date = date;
        this.amount = amount;
        this.name = name;
        this.type = type;
        this.medium = medium;
        this.category = category;
        this.user = user;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

