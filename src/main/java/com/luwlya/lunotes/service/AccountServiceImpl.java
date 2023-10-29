package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.account.AccountResponse;
import com.luwlya.lunotes.dto.account.CreateAccountRequest;
import com.luwlya.lunotes.dto.account.UpdateAccountRequest;
import com.luwlya.lunotes.exception.AccountAlreadyExistsException;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.model.AccountStatus;
import com.luwlya.lunotes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final Clock clock;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

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
        try {
            accountRepository.insert(account);
        } catch (DuplicateKeyException e) {
            if (e.getMessage() != null && e.getMessage().contains("accounts_email_key")) {
                throw new AccountAlreadyExistsException(request.email());
            }
            throw new RuntimeException(e);
        }
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
        Account account = accountRepository.get(id);
        Account updatedAccount = new Account(id,
                update.name() != null ? update.name() : account.name(),
                update.email() != null ? update.email() : account.email(),
                update.password() != null ? passwordEncoder.encode(update.password()) : account.passwordHash(),
                account.createdAt(),
                OffsetDateTime.now(),
                account.status());
        try {
            accountRepository.update(updatedAccount);
        } catch (DuplicateKeyException e) {
            if (e.getMessage() != null && e.getMessage().contains("accounts_email_key")) {
                throw new AccountAlreadyExistsException(updatedAccount.email());
            }
            throw new RuntimeException(e);
        }
        return dto(updatedAccount);
    }
}
