package com.telusko.bankingapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.telusko.bankingapplication.bankingObjects.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Account findByAccountNo(String account_no);

    boolean existsByAccountNo(String account_no);

}
