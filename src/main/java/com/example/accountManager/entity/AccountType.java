package com.example.accountManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.accountManager.util.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeName typeName;

    @OneToMany(mappedBy = "accountType")
    private Set<Account> accounts;

    public enum TypeName {
        SAVINGS, CHECKING, FIXED_DEPOSIT
    }
}
