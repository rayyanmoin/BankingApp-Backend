package com.telusko.bankingapplication.bankingObjects;

import jakarta.persistence.*;


@Entity
@Table(name = "Accounts")
public class Account {

    private long id;
    private long user_id;
    private double balance;
    private String accountNo;

    private String PIN;

    public Account(){

    }

    public Account(long id, long user_id, double balance, String accountNo, String PIN){
        this.id = id;
        this.user_id = user_id;
        this.balance = balance;
        this.accountNo = accountNo;
        this.PIN = PIN;
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


    @Column(name = "account_no", nullable = false)
    public String getAccountNo(){
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }


    @Column(name = "PIN", nullable = false)
    public String getPIN(){
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
