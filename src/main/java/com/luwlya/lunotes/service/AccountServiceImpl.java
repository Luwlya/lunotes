package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.account.AccountResponse;
import com.luwlya.lunotes.dto.account.CreateAccountRequest;
import com.luwlya.lunotes.dto.account.UpdateAccountRequest;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.model.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private Clock clock;

    @Autowired
    public AccountServiceImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = new Account(
                UUID.randomUUID(),
                request.name(),
                request.email(),
                request.password(),
                OffsetDateTime.now(clock),
                OffsetDateTime.now(clock),
                AccountStatus.ACTIVE);
        return dto(account);
    }

    private AccountResponse dto(Account account) {
        return new AccountResponse(
                account.id(),
                account.name(),
                account.email(),
                account.createdAt(),
                account.updatedAt(),
                AccountStatus.ACTIVE);
    }

    @Override
    public AccountResponse updateAccount(UUID id, UpdateAccountRequest update) {
        return null;
    }
}
