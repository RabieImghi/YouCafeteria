package org.rabie.youcafeteria.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.rabie.hunters_league.util.JwtOAuth2Filter;
import org.rabie.youcafeteria.util.JwtRequestFilter;
import org.rabie.youcafeteria.util.JwtUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class DelegatingJwtFilter extends OncePerRequestFilter {

    private final JwtRequestFilter jwtRequestFilter;
    //private final JwtOAuth2Filter jwtOAuth2Filter;
    private final JwtUtil jwtUtil;

    public DelegatingJwtFilter(JwtRequestFilter jwtRequestFilter,  JwtUtil jwtUtil) {
        this.jwtRequestFilter = jwtRequestFilter;
        // this.jwtOAuth2Filter = jwtOAuth2Filter;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtRequestFilter.doFilter(request, response, filterChain);

        } else
            filterChain.doFilter(request, response);
    }
}