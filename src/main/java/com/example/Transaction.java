package com.example;

import java.time.LocalDateTime;

/**
 * Created by jessicatracy on 2/3/17.
 */
public class Transaction {
    private String date;
    private double amount;
    private String name;
    private TransactionType transactionType;
    private TransactionMedium transactionMedium;

    public Transaction() {
    }

    public Transaction(String date, String name, double amount, TransactionType transactionType, TransactionMedium transactionMedium) {
        this.date = date;
        this.amount = amount;
        this.name = name;
        this.transactionType = transactionType;
        this.transactionMedium = transactionMedium;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionMedium getTransactionMedium() {
        return transactionMedium;
    }

    public void setTransactionMedium(TransactionMedium transactionMedium) {
        this.transactionMedium = transactionMedium;
    }
}

