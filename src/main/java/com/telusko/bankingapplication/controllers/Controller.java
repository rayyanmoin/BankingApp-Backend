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
    public List<User> getUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/accounts")
    public List<Account> getAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @GetMapping("/loans")
    public List<Loan> getLoan() {
        List<Loan> loans = loanRepository.findAll();
        return loans;
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestParam("name") String name, @RequestParam("status") String status, @RequestParam("CNIC") String CNIC) {

        if (userRepository.existsByCNIC(CNIC)) {
            return "CNIC already Exits";
        }

        User user = new User();
        user.setName(name);
        user.setStatus(status);
        user.setCNIC(CNIC);
        userRepository.save(user);
        return "User Added Successfully";
    }


    @RequestMapping("/addAccount")
    public String addAccount(@RequestParam("user_id") long user_id, @RequestParam("balance") Double balance) {
        if (!userRepository.existsById(user_id)) {
            return "User with user id Not Found :(";
        }

        List<Account> accounts = accountRepository.findAll();
        List<Account> userAccounts = accounts.stream().filter(account1 -> account1.getUserId() == user_id).collect(Collectors.toList());
        if (userAccounts.size() >= 3) {
            return "You cannot make more than 3 Accounts in this user Id ";
        }
        Account account = new Account();
        account.setBalance(balance);
        account.setUserId(user_id);
        accountRepository.save(account);
    return "Account Created Successfully";
    }

}

