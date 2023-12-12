package com.luwlya.lunotes.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() == null && request.getCookies() != null) {
            var token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).findFirst();
            if (token.isPresent()) {
                try {
                    var key = "xg8kdZSrLoLnM2JFoq3m+xlupDjBp45x+lCsEcD4odQxKzc1XKA4K9eyWNW9IKPC0Ov68tZo3M/+Ll08DPEC2g==";

                    String userId = Jwts.parser()
                            .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)))
                            .build()
                            .parseSignedClaims(token.get().getValue())
                            .getPayload()
                            .getSubject();
                    var authorities = List.of(new SimpleGrantedAuthority("USER"));
                    var auth = new UsernamePasswordAuthenticationToken(userId, "", authorities);
                    context.setAuthentication(auth);
                } catch (Exception ignore) {
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}

