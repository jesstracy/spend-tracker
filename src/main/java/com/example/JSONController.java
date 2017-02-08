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

    @RequestMapping(path = "/seeAllTransactions.json", method = RequestMethod.POST)
    public void seeAllTransactions() {
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
}
