package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 2/6/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    ArrayList<Transaction> findAllByType(String type);
    ArrayList<Transaction> findAllByDate(String date);
}
