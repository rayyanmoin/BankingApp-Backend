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

import java.util.*;
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
    public String addUser(@RequestParam("name") String name, @RequestParam("status") String status, @RequestParam("CNIC") String CNIC,
                          @RequestParam("fatherName") String fatherName, @RequestParam("phoneNo") String phoneNo,
                          @RequestParam("gender") String gender, @RequestParam("profession") String profession,
                          @RequestParam("age") Integer age) {

        if (userRepository.existsByCNIC(CNIC)) {
            return "CNIC already Exits";
        }

        User user = new User();
        user.setName(name);
        user.setStatus(status);
        user.setCNIC(CNIC);
        user.setFatherName(fatherName);
        user.setPhoneNo(phoneNo);
        user.setGender(gender);
        user.setProfession(profession);
        user.setAge(age);

        userRepository.save(user);
        return "User Added Successfully";
    }


    @RequestMapping("/addAccount")
    public String addAccount(@RequestParam("user_id") long user_id, @RequestParam("balance") Double balance, @RequestParam("PIN") String PIN) {
        if (!userRepository.existsById(user_id)) {
            return "User with user id Not Found :(";
        }

        List<Account> accounts = accountRepository.findAll();
        List<Account> userAccounts = accounts.stream().filter(account1 -> account1.getUserId() == user_id).collect(Collectors.toList());
        if (userAccounts.size() >= 3) {
            return "You cannot make more than 3 Accounts in this user Id ";
        }
        if (PIN.length() > 4||PIN.length() < 4 ) {
            return "PIN should be exact four digit";
        }
        Account account = new Account();
        account.setBalance(balance);
        account.setUserId(user_id);
        account.setAccountNo(generateRandom10DigitNumber());
        account.setPIN(PIN);
        accountRepository.save(account);
    return "Account Created Successfully";
    }
    // Method to generate a random 10-digit number for Account Id
    private String generateRandom10DigitNumber() {
        Random random = new Random();
        long randomNumber = random.nextLong() % 10000000000L; // Ensure it's 10 digits
        if(randomNumber < 0) {
            randomNumber *= -1;
        }
        return String.format("%010d", randomNumber);
    }
    @RequestMapping("/addloan")
    public String getLoan(@RequestParam("userId") long userId,  @RequestParam("accountNumber") String accountNumber, @RequestParam("PIN") String PIN,@RequestParam("sanctionAmount") Double sanctionAmount) {
        if (!userRepository.existsById(userId)) {
            return "User with user id Not Found :(";
        }
        if (!accountRepository.existsByAccountNo(accountNumber)) {
            return "Account Not Found :(";
        }
        Account account = accountRepository.findByAccountNo(accountNumber);
        if(!account.getPIN().trim().equals(PIN.trim())){
            return "Incorrect PIN :(";
        }
        List<Account> accounts = accountRepository.findAll();
        List<Account> userAccounts = accounts.stream().filter(account1 -> account1.getUserId() == userId).collect(Collectors.toList());
        double totalBalance = 0;
        for (Account userAccount : userAccounts) {
            totalBalance += userAccount.getBalance();
        }
        if (2*sanctionAmount < totalBalance) {
            Loan loan = new Loan();
            loan.setSanctionAmount(sanctionAmount);
            loan.setUserId(userId);
            loanRepository.save(loan);
            return "Loan Amount Aproved";
        }
        return "Loan amount requested exceeds limit!";

    }
    @RequestMapping(value = "/depositmoney")
    public String depositMoney(@RequestParam(value = "accountId") String accountId, @RequestParam("amount") double amount, @RequestParam("PIN") String PIN) throws ResourceNotFoundException {
        Account account = accountRepository.findByAccountNo(accountId);

        if (account == null) {
            throw new ResourceNotFoundException("Account not for for this id :: " + accountId);
        }
        // account found
        // PIN check
        // Request Param PIN compare account.getPIN()
        // equal ok
        // if not return "Incorrect PIN"

        if (account.getPIN().equals(PIN)) {
            double initialBalance = account.getBalance();
            account.setBalance(initialBalance + amount);
            accountRepository.save(account);
            return "Amount Deposited successfully in Account";
        }

        return "Incorrect PIN :(";
    }

    @RequestMapping(value = "/withdrawmoney")
    public String withdrawMoney(@RequestParam(value = "accountId") String accountId, @RequestParam("amount") double amount, @RequestParam("PIN") String PIN) throws ResourceNotFoundException {
        Account account = accountRepository.findByAccountNo(accountId);
        if(account == null){
        throw new ResourceNotFoundException("Account not for for this id :: " + accountId);
        }
        // PIN check same as deposit
        if (account.getPIN().equals(PIN)) {
            double initialBalance = account.getBalance();
            if (amount > initialBalance) {
                return "Withdrawal amount exceeded";

            }
            account.setBalance(initialBalance-amount);
            accountRepository.save(account);
            return "Amount Withdraw successful for Account";
        }
        return "Cannot withdraw PIN error:(";
    }
    @GetMapping("/usersForDropdown")
    public List<Map<String, Object>> getUsers() {
        List<User> users = userRepository.findAll().stream()
                .filter(user -> "active".equalsIgnoreCase(user.getStatus()))
                .collect(Collectors.toList());;
        List<Map<String, Object>> result = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("username", user.getName());
            result.add(userMap);
        }

        return result;
    }

}

