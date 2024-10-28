package com.ford.credit.demo.application.service.account;

import com.ford.credit.demo.application.domain.Account;
import com.ford.credit.demo.application.port.in.account.DepositToAccountUseCase;
import com.ford.credit.demo.application.port.in.account.WithdrawFromAccountUseCase;
import com.ford.credit.demo.application.port.in.account.TransferBetweenAccountsUseCase;
import com.ford.credit.demo.application.port.out.ReadAccountPort;
import com.ford.credit.demo.application.port.out.StoreAcountPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService implements DepositToAccountUseCase, WithdrawFromAccountUseCase, TransferBetweenAccountsUseCase {

    private final ReadAccountPort readAccountPort;
    private final StoreAcountPort storeAcountPort;

    @Override
    public Account depositToAccount(DepositRequestCommand command) {

        // Buscar cuenta por id y depositar dinero
        Account account =  this.readAccountPort.findAccountByAccountId(command.accountId())
                .deposit(command.amount());
        Assert.notNull(account, "Account not found");

        // Guardar cambios
        storeAcountPort.store(account);

        return account;
    }

    @Override
    public Account withdrawFromAccount(WithdrawRequestCommand command) {
        // Buscar cuenta por id y retirar dinero
        Account account =  this.readAccountPort.findAccountByAccountId(command.accountId())
                .withdraw(command.amount());
        Assert.notNull(account, "Account not found");

        // Guardar cambios
        storeAcountPort.store(account);

        return account;
    }

    @Override
    @Transactional
    public Account transferBetweenAccounts(TransferBetweenAccountsCommand command) {
        // Busca cuenta origen y retira dinero
        Account fromAccount = this.readAccountPort.findAccountByAccountId(command.fromAccountId())
                .withdraw(command.amount());
        Assert.notNull(fromAccount, "Account not found");

        // Busca cuenta destino y añade dinero
        Account toAccount = this.readAccountPort.findAccountByAccountId(command.toAccountId())
                .deposit(command.amount());
        Assert.notNull(toAccount, "Account not found");

        // Guardar cuentas
        storeAcountPort.store(fromAccount);
        storeAcountPort.store(toAccount);

        return fromAccount;
    }


}
