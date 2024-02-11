package com.telusko.bankingapplication.controllers;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telusko.bankingapplication.bankingObjects.Account;
import com.telusko.bankingapplication.bankingObjects.Amount;
import com.telusko.bankingapplication.bankingObjects.Loan;
import com.telusko.bankingapplication.bankingObjects.User;
import com.telusko.bankingapplication.exceptions.ResourceNotFoundException;
import com.telusko.bankingapplication.repositories.AccountRepository;
import com.telusko.bankingapplication.repositories.LoanRepository;
import com.telusko.bankingapplication.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoanRepository loanRepository;


    @GetMapping("/users")
    public List<User> getUser(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/accounts")
    public List<Account> getAccount(){
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @GetMapping("/loans")
    public List<Loan> getLoan(){
        List<Loan> loans = loanRepository.findAll();
        return loans;
    }

    @RequestMapping("/addUser")
    public User addUser(@RequestParam("name") String name,@RequestParam("status") String status,@RequestParam("CNIC") String CNIC){
        User user = new User();
        user.setName(name);
        user.setStatus(status);
        user.setCNIC(CNIC);
        return userRepository.save(user);
    }

}
