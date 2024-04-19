package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.account.AccountResponse;
import com.luwlya.lunotes.dto.account.CreateAccountRequest;
import com.luwlya.lunotes.exception.AccountAlreadyExistsException;
import com.luwlya.lunotes.model.AccountStatus;
import com.luwlya.lunotes.test.TestAccountRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceImplTest {
    private final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    private final TestAccountRepo accountRepository = new TestAccountRepo();
    AccountServiceImpl unit = new AccountServiceImpl(clock, NoOpPasswordEncoder.getInstance(), accountRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createAccountSuccess() {
        //given
        CreateAccountRequest request = new CreateAccountRequest("Misha", "misha@cat.com", "password");
        //when
        AccountResponse result = unit.createAccount(request);
        //then
        OffsetDateTime now = OffsetDateTime.now(clock);
        AccountResponse expected = new AccountResponse(result.id(), "Misha", "misha@cat.com", now, now, AccountStatus.ACTIVE);
        assertEquals(expected, result);
        assertEquals(1, accountRepository.inserted.size());
        assertEquals(expected.name(), accountRepository.inserted.get(0).name());
        assertEquals(expected.email(), accountRepository.inserted.get(0).email());
    }

    @Test
    void createAccountThrowsOnDuplicateEmail() {
        //given
        CreateAccountRequest request = new CreateAccountRequest("Misha", "misha@cat.com", "password");
        //when //then
        unit.createAccount(request);
        assertThrows(AccountAlreadyExistsException.class, () -> unit.createAccount(request));
    }

    @Test
    void updateAccount() {
    }

    @Test
    void get() {
    }
}