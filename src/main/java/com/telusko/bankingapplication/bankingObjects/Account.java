package com.telusko.bankingapplication.bankingObjects;

import jakarta.persistence.*;


@Entity
@Table(name = "Accounts")
public class Account {

    private long id;
    private long user_id;
    private double balance;

    public Account(){

    }

    public Account(long id, long user_id, double balance){
        this.id = id;
        this.user_id = user_id;
        this.balance = balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId(){
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    @Column(name = "balance", nullable = false)
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
