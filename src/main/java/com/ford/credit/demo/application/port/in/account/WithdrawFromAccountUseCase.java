package com.ford.credit.demo.application.port.in.account;

import com.ford.credit.demo.application.domain.Account;

import java.math.BigDecimal;

public interface WithdrawFromAccountUseCase {
    Account withdrawFromAccount(WithdrawRequestCommand command);

    record WithdrawRequestCommand(Long accountId, BigDecimal amount) {
    }
}
