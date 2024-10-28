package com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.repository;

import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
