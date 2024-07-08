package com.example.accountManager.repository;

import com.example.accountManager.entity.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStatusRepository extends JpaRepository<CustomerStatus, Long> {

}
