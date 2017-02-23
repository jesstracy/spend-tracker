package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 2/6/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    ArrayList<Transaction> findAllByUser(User user);
    ArrayList<Transaction> findAllByUserAndType(User user, String type);
    ArrayList<Transaction> findAllByUserAndDate(User user, String date);
    ArrayList<Transaction> findAllByUserAndDateAndTypeAndMediumAndCategory(User user, String date, String type, String medium, String category);
    ArrayList<Transaction> findAllByUserAndDateAndTypeAndCategory(User user, String date, String type, String category);
    ArrayList<Transaction> findAllByUserAndDateAndTypeAndMedium(User user, String date, String type, String medium);
    ArrayList<Transaction> findAllByUserAndDateAndType(User user, String date, String type);
    ArrayList<Transaction> findAllByUserAndDateAndMediumAndCategory(User user, String date, String medium, String category);
    ArrayList<Transaction> findAllByUserAndDateAndCategory(User user, String date, String category);
    ArrayList<Transaction> findAllByUserAndDateAndMedium(User user, String date, String medium);
    ArrayList<Transaction> findAllByUserAndTypeAndMediumAndCategory(User user, String type, String medium, String category);
    ArrayList<Transaction> findAllByUserAndMediumAndCategory(User user, String medium, String category);
    ArrayList<Transaction> findAllByUserAndTypeAndCategory(User user, String type, String category);
    ArrayList<Transaction> findAllByUserAndTypeAndMedium(User user, String type, String medium);
    ArrayList<Transaction> findAllByUserAndCategory(User user, String category);
    ArrayList<Transaction> findAllByUserAndMedium(User user, String medium);

}
