package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.LoginRequest;
import com.luwlya.lunotes.exception.AccountNotFoundException;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.repository.AccountRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class LoginServiceImpl implements LoginService {
    private AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;

    @Autowired
    public LoginServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, SecretKey secretKey) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = secretKey;
    }

    @Override
    public String login(LoginRequest request) {
        Account account = accountRepository.getByEmail(request.email());
        if (!passwordEncoder.matches(request.password(), account.passwordHash())) {
            throw new AccountNotFoundException(request.email());
        }
        return Jwts.builder().subject(account.id().toString()).signWith(secretKey, Jwts.SIG.HS256).compact();
    }
}
