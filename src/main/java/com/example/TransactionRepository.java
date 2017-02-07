package com.example;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessicatracy on 2/6/17.
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
