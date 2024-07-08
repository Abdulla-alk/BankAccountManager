package com.example.accountManager.entity;

import com.example.accountManager.service.AccountStatusService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private com.example.accountManager.util.AccountStatus statusName;

    @OneToMany(mappedBy = "status")
    private Set<Account> accounts;
}
