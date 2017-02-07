package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jessicatracy on 2/5/17.
 */
@RestController
public class JSONController {
    @Autowired
    TransactionRepository transactionRepo;

    @RequestMapping(path = "/submitTransaction.json", method = RequestMethod.POST)
    public void submitTransaction(@RequestBody Transaction newTransaction) {
        transactionRepo.save(newTransaction);
    }
}
