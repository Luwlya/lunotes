package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.account.AccountResponse;
import com.luwlya.lunotes.dto.account.CreateAccountRequest;
import com.luwlya.lunotes.dto.account.UpdateAccountRequest;

import java.util.UUID;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);

    AccountResponse updateAccount(UUID id, UpdateAccountRequest update);
}
