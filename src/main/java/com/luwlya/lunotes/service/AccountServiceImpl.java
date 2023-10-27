package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.account.AccountResponse;
import com.luwlya.lunotes.dto.account.CreateAccountRequest;
import com.luwlya.lunotes.dto.account.UpdateAccountRequest;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.model.AccountStatus;
import com.luwlya.lunotes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private Clock clock;
    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(Clock clock, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.clock = clock;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = new Account(
                UUID.randomUUID(),
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                OffsetDateTime.now(clock),
                OffsetDateTime.now(clock),
                AccountStatus.ACTIVE);
        accountRepository.insert(account);
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
