package com.luwlya.lunotes.test;

import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.repository.AccountRepository;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestAccountRepo implements AccountRepository {
    public List<Account> inserted = new ArrayList<>();

    @Override
    public void insert(Account account) {
        for (Account account1 : inserted) {
            if (account1.email().equals(account.email())) {
                throw new DuplicateKeyException("accounts_email_key");
            }
        }
        inserted.add(account);
    }

    @Override
    public Account get(UUID id) {
        return null;
    }

    @Override
    public Account getByEmail(String email) {
        return null;
    }

    @Override
    public void update(Account updatedAccount) {

    }
}
