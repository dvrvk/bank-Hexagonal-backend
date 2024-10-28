package com.ford.credit.demo.infrastructure.adapter.in.rest;

import com.ford.credit.demo.application.domain.Account;
import com.ford.credit.demo.application.port.in.account.DepositToAccountUseCase;
import com.ford.credit.demo.application.port.in.account.TransferBetweenAccountsUseCase;
import com.ford.credit.demo.application.port.in.account.WithdrawFromAccountUseCase;
import com.ford.credit.demo.infrastructure.adapter.in.rest.dto.AccountResponse;
import com.ford.credit.demo.infrastructure.adapter.in.rest.mappers.AccountResponseMapper;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.model.AccountEntity;
import com.ford.credit.demo.infrastructure.adapter.out.db2.jpa.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/account")
@RestController
@Validated
public class AccountController {

    private final DepositToAccountUseCase depositToAccountUseCase;
    private final WithdrawFromAccountUseCase withdrawFromAccountUseCase;
    private final TransferBetweenAccountsUseCase transferBetweenAccountsUseCase;

    // Borrar
    private final AccountRepository accountRepository;

    @PostMapping("/deposit")
    public ResponseEntity<AccountResponse> depositToAccount(@RequestBody DepositToAccountUseCase.DepositRequestCommand command) {
        AccountResponse accountResponse = AccountResponseMapper.INSTANCE.accountToAccountResponse(depositToAccountUseCase.depositToAccount(command));
        return ResponseEntity.ok().body(accountResponse);

    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountResponse> withdrawFromAccount(@RequestBody WithdrawFromAccountUseCase.WithdrawRequestCommand command) {
        AccountResponse accountResponse = AccountResponseMapper.INSTANCE.accountToAccountResponse(withdrawFromAccountUseCase.withdrawFromAccount(command));
        return ResponseEntity.ok().body(accountResponse);
    }

    @PostMapping("/transfer")
    public ResponseEntity<AccountResponse> transferBetweenAccounts(@RequestBody TransferBetweenAccountsUseCase.TransferBetweenAccountsCommand command) {
        AccountResponse accountResponse = AccountResponseMapper.INSTANCE.accountToAccountResponse(transferBetweenAccountsUseCase.transferBetweenAccounts(command));
        return ResponseEntity.ok().body(accountResponse);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/test2")
    public ResponseEntity<List<AccountEntity>> test2() {
        return ResponseEntity.ok().body(accountRepository.findAll());
    }
}
