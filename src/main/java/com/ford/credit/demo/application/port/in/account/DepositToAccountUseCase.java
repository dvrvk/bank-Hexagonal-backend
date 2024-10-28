package com.ford.credit.demo.application.port.in.account;

import com.ford.credit.demo.application.domain.Account;
import lombok.Getter;

import java.math.BigDecimal;

public interface DepositToAccountUseCase {
    Account depositToAccount(DepositRequestCommand command);

    record DepositRequestCommand(Long accountId, BigDecimal amount) {
    }
}
