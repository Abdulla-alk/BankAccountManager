package com.example.accountManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.accountManager.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
