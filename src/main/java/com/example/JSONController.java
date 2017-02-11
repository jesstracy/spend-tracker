package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 2/5/17.
 */
@RestController
public class JSONController {
    @Autowired
    TransactionRepository transactionRepo;

    @RequestMapping(path = "/submitTransaction.json", method = RequestMethod.POST)
    public void submitTransaction(@RequestBody Transaction newTransaction) {
//        System.out.println(newTransaction.getAmount());
//        System.out.println(newTransaction.getName());
//        System.out.println(newTransaction.getDate());
//        System.out.println(newTransaction.getCategory());
//        System.out.println(newTransaction.getMedium());
//        System.out.println(newTransaction.getType());
        transactionRepo.save(newTransaction);
    }

    @RequestMapping(path = "/getAllTransactions.json", method = RequestMethod.POST)
    public ArrayList<Transaction> getAllTransactions() {
        Iterable<Transaction> iterableTransactions = transactionRepo.findAll();
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        for (Transaction transaction : iterableTransactions) {
            allTransactions.add(transaction);
        }
        return allTransactions;
    }

    public void printAllTransactions() {
        Iterable<Transaction> allTransactions = transactionRepo.findAll();
        System.out.println("******* All Transactions *********");
        for (Transaction transaction : allTransactions) {
            System.out.println("Date: " + transaction.getDate());
            System.out.println("Name: " + transaction.getName());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("Type: " + transaction.getType());
            System.out.println("Medium: " + transaction.getMedium());
            System.out.println("Category: " + transaction.getCategory());
            System.out.println();
        }
        System.out.println("*********************************");
    }

    @RequestMapping(path = "/getTransactionsByType.json", method = RequestMethod.POST)
    public ArrayList<Transaction> getTransactionsByType(@RequestBody String type) {
        if (type.equals("Deposit") || type.equals("Withdrawal")) {
            ArrayList<Transaction> allByType = transactionRepo.findAllByType(type);
            return allByType;
        } else {
            System.out.println("Parameter invalid");
            return null;
        }
    }

    @RequestMapping(path = "/getTransactionsByDate.json", method = RequestMethod.POST)
    public ArrayList<Transaction> getTransactionsByDate(@RequestBody String date) {
        ArrayList<Transaction> allByMonth = transactionRepo.findAllByDate(date);
        return allByMonth;
    }

    @RequestMapping(path = "/getBalance.json", method = RequestMethod.POST)
    public double getBalance(@RequestBody String month) {
        double balance = 0.0;
        ArrayList<Transaction> allByMonth = transactionRepo.findAllByDate(month);
        for (Transaction transaction : allByMonth) {
            if (transaction.getType().equals("Withdrawal")) {
                balance -= transaction.getAmount();
            } else if (transaction.getType().equals("Deposit")) {
                balance += transaction.getAmount();
            }
        }
        return balance;
    }
}
