package com.example.springboottransaction.model;


import javax.persistence.*;

@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String username;

    @Column
    private float balance;


    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
