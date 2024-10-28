package com.ford.credit.demo.application.port.out;

import com.ford.credit.demo.application.domain.Account;

import java.util.Optional;

public interface ReadAccountPort {
    Account findAccountByAccountId(Long accountId);
}
