package com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private BigDecimal balance;

        @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<MovementsEntity> movements;

}
