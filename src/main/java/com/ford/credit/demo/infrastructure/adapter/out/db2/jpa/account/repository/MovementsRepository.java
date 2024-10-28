package com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.repository;

import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model.AccountEntity;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model.MovementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MovementsRepository extends JpaRepository<MovementsEntity, Long> {

    List<MovementsEntity> findByAccountId(Long accountId);

}
