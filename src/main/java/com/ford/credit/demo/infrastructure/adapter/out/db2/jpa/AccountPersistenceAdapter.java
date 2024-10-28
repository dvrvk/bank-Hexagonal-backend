package com.ford.credit.demo.infrastructure.adapter.out.db2.jpa;

import com.ford.credit.demo.application.domain.Account;
import com.ford.credit.demo.application.port.out.ReadAccountPort;
import com.ford.credit.demo.application.port.out.StoreAcountPort;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model.AccountEntity;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model.MovementsEntity;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.repository.AccountRepository;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.repository.MovementsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class AccountPersistenceAdapter implements ReadAccountPort, StoreAcountPort {

    private final AccountRepository accountRepository;
    private final MovementsRepository movementsRepository;

    @Override
    public Account findAccountByAccountId(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow();

        return Account.byExistenceAccount(accountEntity.getId(), accountEntity.getBalance(), accountEntity.getMovements().stream().map(MovementsEntity::getAmount).collect(Collectors.toList()));
    }

    @Override
    public Account store(Account account) {
        // Buscar la entidad AccountEntity existente o crear una nueva si no existe
        AccountEntity accountEntity = account.getId() != null
                ? accountRepository.findById(account.getId()).orElse(new AccountEntity())
                : new AccountEntity();

        // Actualizar los campos de la entidad AccountEntity
        accountEntity.setBalance(account.getBalance());

        // Mapear los movimientos de la cuenta a MovementsEntity y asociarlos a la cuenta
        List<MovementsEntity> movementsEntities = new ArrayList<>();
        for (BigDecimal movement : account.getMovements()) {
            MovementsEntity movementsEntity = new MovementsEntity(null, accountEntity, movement);
            movementsEntities.add(movementsEntity);
        }

        // Actualizar la lista de movimientos en la entidad AccountEntity
        if (accountEntity.getMovements() != null) {
            accountEntity.getMovements().clear();
            accountEntity.getMovements().addAll(movementsEntities);
        } else {
            accountEntity.setMovements(movementsEntities);
        }

        // Guardar la entidad AccountEntity y los movimientos
        accountEntity = accountRepository.save(accountEntity);

        // Devolver la cuenta actualizada
        return Account.byExistenceAccount(accountEntity.getId(), accountEntity.getBalance(),
                accountEntity.getMovements().stream().map(MovementsEntity::getAmount).collect(Collectors.toList()));
    }
}
