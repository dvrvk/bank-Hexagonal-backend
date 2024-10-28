package com.ford.credit.demo.application.port.out;

import com.ford.credit.demo.application.domain.Account;

public interface StoreAcountPort {
    Account store(Account account);
}
