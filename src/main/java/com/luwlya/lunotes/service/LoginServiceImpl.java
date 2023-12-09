package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.LoginRequest;
import com.luwlya.lunotes.exception.AccountNotFoundException;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequest request) {
        Account account = accountRepository.getByEmail(request.email());
        if(!passwordEncoder.matches(request.password(), account.passwordHash())){
           throw new AccountNotFoundException(request.email());
        }
        return request.email();
    }
}
