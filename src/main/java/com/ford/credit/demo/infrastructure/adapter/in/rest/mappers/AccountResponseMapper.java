package com.ford.credit.demo.infrastructure.adapter.in.rest.mappers;

import com.ford.credit.demo.application.domain.Account;
import com.ford.credit.demo.infrastructure.adapter.in.rest.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountResponseMapper {
    AccountResponseMapper INSTANCE = Mappers.getMapper(AccountResponseMapper.class);

    AccountResponse accountToAccountResponse(Account account);
}
