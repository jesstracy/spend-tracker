package com.example;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by jessicatracy on 2/6/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
