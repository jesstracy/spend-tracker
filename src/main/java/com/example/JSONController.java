package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jessicatracy on 2/5/17.
 */
@RestController
public class JSONController {
    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    UserRepository userRepo;

    User currentUser;

    @RequestMapping(path = "/login.json", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        User retrievedUser = userRepo.findByEmail(user.getEmail());

        if (retrievedUser != null) {
            if (user.getPassword().equals(retrievedUser.getPassword())) {
                currentUser = retrievedUser;
                return retrievedUser;
            }
        }
        return null;
    }

    @RequestMapping(path = "/register.json", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        userRepo.save(user);
        User retrievedUser = userRepo.findByEmail(user.getEmail());
        currentUser = retrievedUser;
        return retrievedUser;
    }

    @RequestMapping(path = "/submitTransaction.json", method = RequestMethod.POST)
    public void submitTransaction(@RequestBody Transaction newTransaction) {
        newTransaction.setUser(currentUser);
        transactionRepo.save(newTransaction);
    }

    @RequestMapping(path = "/getAllTransactions.json", method = RequestMethod.POST)
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        if (currentUser != null) {
            Iterable<Transaction> iterableTransactions = transactionRepo.findAllByUser(currentUser);
            for (Transaction transaction : iterableTransactions) {
                allTransactions.add(transaction);
            }
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
            ArrayList<Transaction> allByType = transactionRepo.findAllByUserAndType(currentUser,type);
            return allByType;
        } else {
            System.out.println("Parameter invalid");
            return null;
        }
    }

    @RequestMapping(path = "/getTransactionsByDate.json", method = RequestMethod.POST)
    public ArrayList<Transaction> getTransactionsByDate(@RequestBody String date) {
        ArrayList<Transaction> allByMonth = transactionRepo.findAllByUserAndDate(currentUser,date);
        return allByMonth;
    }

    @RequestMapping(path = "/deleteTransaction.json", method = RequestMethod.POST)
    public ArrayList<Transaction> deleteTransaction(@RequestBody int id) {
        transactionRepo.delete(id);
        ArrayList<Transaction> allTransactions = getAllTransactions();
        return allTransactions;
    }

    @RequestMapping(path = "/getBalance.json", method = RequestMethod.POST)
    public double getBalance(@RequestBody String month) {
        double balance = 0.0;
        ArrayList<Transaction> allByMonth = transactionRepo.findAllByUserAndDate(currentUser,month);
        for (Transaction transaction : allByMonth) {
            if (transaction.getType().equals("Withdrawal")) {
                balance -= transaction.getAmount();
            } else if (transaction.getType().equals("Deposit")) {
                balance += transaction.getAmount();
            }
        }
        return balance;
    }

    @RequestMapping(path = "/submitDisplayOptions.json", method = RequestMethod.POST)
    public ArrayList<Transaction> submitDisplayOptions(@RequestBody Transaction transactionEx) {
        boolean dateSet = false, typeSet = false, categorySet = false, mediumSet = false;
        ArrayList<Transaction> transactions = new ArrayList<>();

        if (transactionEx.getDate() != null) {
            dateSet = true;
//            ArrayList<Transaction> allByDate = transactionRepo.findAllByUserAndDate(currentUser, transactionEx.getDate());
        }
        if (transactionEx.getType() != null) {
            typeSet = true;
//            ArrayList<Transaction> allByDate = transactionRepo.findAllByUserAndDate(currentUser, transactionEx.getDate());
        }
        if (transactionEx.getCategory() != null) {
            categorySet = true;
//            ArrayList<Transaction> allByDate = transactionRepo.findAllByUserAndDate(currentUser, transactionEx.getDate());
        }
        if (transactionEx.getMedium() != null) {
            mediumSet = true;
//            ArrayList<Transaction> allByDate = transactionRepo.findAllByUserAndDate(currentUser, transactionEx.getDate());
        }
        if (dateSet) {
            if (typeSet) {
                if (categorySet) {
                    if (mediumSet) {
                        //return date, type, category, medium
                    } else {
                        //return date, type, category
                    }
                } else if (mediumSet) {
                    //return date, type, medium
                } else {
                    //return date and type
                }
            } else if (categorySet) {
                //more tree, not date, type
                if (mediumSet) {
                    //return date, category, medium
                } else {
                    //reutrn date, category
                }
            } else if (mediumSet) {
                //return date and medium
            } else {
                //return date only
            }
        } else if (typeSet) {
            if (categorySet) {
                if (mediumSet) {
                    //return type, category, medium
                } else {
                    //return type, category
                }
            } else if (mediumSet) {
                //return type, medium
            } else {
                // return type only
            }
        } else if (categorySet) {
            if (mediumSet) {
                //return category, medium
            } else {
                //return category only
            }
        } else if (mediumSet) {
            //return medium only
        } else {
            return transactions;
        }
        return null;
    }
}
