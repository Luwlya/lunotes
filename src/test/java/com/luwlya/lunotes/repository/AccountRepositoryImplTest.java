package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.model.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountRepositoryImplTest {
    private AccountRepositoryImpl unit;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5431/lunotes");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        unit = new AccountRepositoryImpl(jdbcTemplate);
        jdbcTemplate.update("TRUNCATE TABLE accounts CASCADE");
    }

    @Test
    void insert() {
        OffsetDateTime utcNow = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC);
        Account account = new Account(UUID.randomUUID(), "Headshot", "head@cat.com", "hash", utcNow, utcNow.plusSeconds(1), AccountStatus.ACTIVE);
        unit.insert(account);
        Account actualAccount = unit.get(account.id());
        assertEquals(account, actualAccount);
    }

    @Test
    void update() {
        OffsetDateTime utcNow = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC);
        Account account = new Account(UUID.randomUUID(), "Headshot", "head@cat.com", "hash", utcNow, utcNow.plusSeconds(1), AccountStatus.ACTIVE);
        unit.insert(account);
        Account updated = account.withStatus(AccountStatus.INACTIVE).withName("Cat").withUpdatedAt(utcNow.plusSeconds(10));
        unit.update(updated);
        Account actualAccount = unit.get(account.id());
        assertEquals(updated, actualAccount);
    }
}