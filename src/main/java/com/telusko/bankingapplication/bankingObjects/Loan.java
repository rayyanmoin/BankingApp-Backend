package com.telusko.bankingapplication.bankingObjects;

import jakarta.persistence.*;


@Entity
@Table(name = "Loans")
public class Loan {
    private long id;
    private long user_id;
    private double sanction_amount;

    public Loan() {

    }

    public Loan(long id, long user_id, double sanction_amount) {
        this.id = id;
        this.user_id = user_id;
        this.sanction_amount = sanction_amount;
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

    @Column(name = "sanction_amount", nullable = false)
    public double getSanctionAmount(){
        return sanction_amount;
    }

    public void setSanctionAmount(double sanction_amount) {
        this.sanction_amount = sanction_amount;
    }

}
