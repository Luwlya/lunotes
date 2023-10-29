package com.luwlya.lunotes.http;

import com.luwlya.lunotes.dto.account.AccountResponse;
import com.luwlya.lunotes.dto.account.CreateAccountRequest;
import com.luwlya.lunotes.dto.account.UpdateAccountRequest;
import com.luwlya.lunotes.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
        System.out.println("Create account" + request);
        AccountResponse accountResponse = accountService.createAccount(request);
        return ResponseEntity.ok().body(accountResponse);
    }

    @PatchMapping("/accounts/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable UUID id,
                                                         @RequestBody UpdateAccountRequest update) {
        AccountResponse accountResponse = accountService.updateAccount(id, update);
        return ResponseEntity.ok().body(accountResponse);
    }
}
