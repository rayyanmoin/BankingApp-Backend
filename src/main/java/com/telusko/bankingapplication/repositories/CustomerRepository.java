package com.telusko.bankingapplication.repositories;

import com.telusko.bankingapplication.bankingObjects.CustomerHelp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerHelp, Long> {
}
