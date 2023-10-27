package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Account account) {
        jdbcTemplate.update(
                "INSERT INTO accounts(id, name, email, password_hash, created_at, updated_at, status) " +
                        "VALUES (?,?,?,?,?,?,?)",
                account.id(),
                account.name(),
                account.email(),
                account.passwordHash(),
                account.createdAt(),
                account.updatedAt(),
                account.status().name());
    }
}
