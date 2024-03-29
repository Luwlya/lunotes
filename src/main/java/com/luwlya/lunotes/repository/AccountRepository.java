package com.luwlya.lunotes.repository;

import com.luwlya.lunotes.model.Account;

import java.util.UUID;

public interface AccountRepository {
    void insert(Account account);

    Account get(UUID id);

    Account getByEmail(String email);

    void update(Account updatedAccount);
}
