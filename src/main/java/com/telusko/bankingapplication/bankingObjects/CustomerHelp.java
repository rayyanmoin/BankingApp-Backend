package com.telusko.bankingapplication.bankingObjects;

import jakarta.persistence.*;

@Entity
@Table(name = "customerhelp")
public class CustomerHelp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "problem_type", nullable = true, length = 100)
    private String problemType;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "status", nullable = true, length = 50)
    private String status;

    // Default constructor required by JPA
    public CustomerHelp() {
    }

    public CustomerHelp(Long id, Long userId, String problemType, String description, String status) {
        this.id = id;
        this.userId = userId;
        this.problemType = problemType;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
