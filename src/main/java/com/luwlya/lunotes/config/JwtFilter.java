package com.luwlya.lunotes.config;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.ArrayList;
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
        if (request.getCookies() != null){
        Optional<Cookie> token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("token"))
                .findFirst();
        if(token.isPresent()){
            User user = new User(token.get().getValue(), "", List.of(new SimpleGrantedAuthority("USER")));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "", new ArrayList<>()));
        } }
        filterChain.doFilter(request,response);
    }

}

