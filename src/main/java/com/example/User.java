package com.example;

import javax.persistence.*;

/**
 * Created by jessicatracy on 2/10/17.
 */
@Entity
@Table(name = "users")
public class User {
    @GeneratedValue
    @Id
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private double paycheck;

    public User() {
    }

    public User(String email, String password, double paycheck) {
        this.email = email;
        this.password = password;
        this.paycheck = paycheck;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getPaycheck() {
        return paycheck;
    }

    public void setPaycheck(double paycheck) {
        this.paycheck = paycheck;
    }
}
