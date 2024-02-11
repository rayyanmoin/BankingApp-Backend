package com.telusko.bankingapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.telusko.bankingapplication.bankingObjects.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCNIC(String CNIC);

    boolean existsById(long user_id);

}
