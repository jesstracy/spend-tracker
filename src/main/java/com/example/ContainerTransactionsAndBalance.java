package com.example;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 3/11/17.
 */
public class ContainerTransactionsAndBalance {
    ArrayList<Transaction> transactions;
    double balance;

    public ContainerTransactionsAndBalance() {
    }

    public ContainerTransactionsAndBalance(ArrayList<Transaction> transactions, double balance) {
        this.transactions = transactions;
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
