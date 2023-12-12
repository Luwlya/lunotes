package com.luwlya.lunotes.service;

import com.luwlya.lunotes.dto.LoginRequest;
import com.luwlya.lunotes.exception.AccountNotFoundException;
import com.luwlya.lunotes.model.Account;
import com.luwlya.lunotes.repository.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.Base64;

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
        var key = "xg8kdZSrLoLnM2JFoq3m+xlupDjBp45x+lCsEcD4odQxKzc1XKA4K9eyWNW9IKPC0Ov68tZo3M/+Ll08DPEC2g==";

        var token = Jwts.builder().subject(account.id().toString())
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)), Jwts.SIG.HS512)
                .compact();
        return token;
    }

    public static void main(String[] args) {
        var key = Jwts.SIG.HS512.key().build();
        String encoded = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encoded);
    }
}
