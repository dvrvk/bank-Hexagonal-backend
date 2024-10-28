package com.ford.credit.demo.application.port.in.account;

import com.ford.credit.demo.application.domain.Account;

import java.math.BigDecimal;

public interface TransferBetweenAccountsUseCase {
    Account transferBetweenAccounts(TransferBetweenAccountsCommand command);

    record TransferBetweenAccountsCommand(Long fromAccountId, Long toAccountId, BigDecimal amount) {
    }
}
