package com.luwlya.lunotes.config;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JwtFilter implements Filter {
    private final SecretKey key;

    @Autowired
    public JwtFilter(SecretKey key) {
        this.key = key;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getCookies() != null) {
            Optional<Cookie> token = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("token"))
                    .findFirst();
            token.ifPresent(cookie -> {
                try {
                    String userId = Jwts.parser().verifyWith(key).build().parseSignedClaims(token.get().getValue()).getPayload().getSubject();
                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                    var user = new UsernamePasswordAuthenticationToken(UUID.fromString(userId), "", authorities);
                    SecurityContextHolder.getContext().setAuthentication(user);
                } catch (Exception ignored) {
                }
            });
        }
        filterChain.doFilter(request, response);
    }

}

