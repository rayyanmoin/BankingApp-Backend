package com.telusko.bankingapplication.bankingObjects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {
    private long id;
    private String name;
    private String status;
    private String CNIC;
    private String fatherName;
    private String phoneNo;
    private String gender;
    private String profession;
    private Integer age;

    public User() {
    }

    public User(long id, String name, String status, String CNIC, String fatherName,
                String phoneNo, String gender, String profession, Integer age) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.CNIC = CNIC;
        this.fatherName = fatherName;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.profession = profession;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Column(name = "user_name", nullable = false)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", nullable = false)
    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "CNIC", nullable = false)
    public String getCNIC(){
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    @Column(name = "FATHER_NAME", nullable = false)
    public String getFatherName(){
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Column(name = "PHONE_NO", nullable = false)
    public String getPhoneNo(){
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "GENDER", nullable = false)
    public String getGender(){
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "PROFESSION", nullable = false)
    public String getProfession(){
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Column(name = "AGE", nullable = false)
    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
