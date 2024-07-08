package com.example.accountManager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String idCardNumber;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;


    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private CustomerStatus status;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

}
