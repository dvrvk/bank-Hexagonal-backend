package com.ford.credit.demo.application.domain;


import lombok.Getter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public final class Account {

    private final Long id;
    private final BigDecimal balance;
    private final List<BigDecimal> movements;

    private Account(Long id, BigDecimal balance, List<BigDecimal> movements) {
        this.id = id;
        this.balance = balance;
        this.movements = List.copyOf(movements);
    }

    public static Account createAccount() {
        return new Account(null, BigDecimal.ZERO, List.of());
    }

    public static Account byExistenceAccount(Long id, BigDecimal balance, List<BigDecimal> movements) {
        Assert.isTrue(balance.equals(movements.stream().reduce(BigDecimal.ZERO, BigDecimal::add)), "Balance must be zero for existing accounts");
        Account account = new Account(id, balance, movements);
        return account;
    }


    public Account withdraw(BigDecimal amount) {
        Assert.isTrue(amount.doubleValue() >= 0, "La cantidad a retirar tiene que ser mayor que 0");
        Assert.isTrue(balance.compareTo(amount) >= 0, "Fondos insuficientes");
        List<BigDecimal> newMovements = new ArrayList<>(movements);
        newMovements.add(amount.negate());

        return new Account(id, balance.subtract(amount), newMovements);
    }

    public Account deposit(BigDecimal amount) {
        List<BigDecimal> newMovements = new ArrayList<>(movements);
        newMovements.add(amount);
        return new Account(id, balance.add(amount), newMovements);
    }

}
