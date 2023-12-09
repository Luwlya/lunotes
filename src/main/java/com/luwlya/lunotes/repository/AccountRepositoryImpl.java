package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.exception.AccountNotFoundException;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.model.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public Account get(UUID id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM accounts WHERE id = ?",
                    this::extractAccount,
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(id);
        }
    }

    @Override
    public Account getByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM accounts WHERE email = ?",
                    this::extractAccount,
                    email
            );
        } catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(email);
        }
    }

    @Override
    public void update(Account account) {
        jdbcTemplate.update(
                "UPDATE accounts SET name = ?, email = ?, password_hash = ?, updated_at = ?, status = ? WHERE id = ?",
                account.name(),
                account.email(),
                account.passwordHash(),
                account.updatedAt(),
                account.status().name(),
                account.id());
    }

    private Account extractAccount(ResultSet rs, int i) throws SQLException {
        return new Account(rs.getObject("id", UUID.class),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password_hash"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class),
                AccountStatus.valueOf(rs.getString("status")));
    }
}
