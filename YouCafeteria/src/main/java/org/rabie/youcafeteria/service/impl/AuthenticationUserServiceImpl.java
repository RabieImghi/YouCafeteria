package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.service.AuthenticationUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUserServiceImpl implements AuthenticationUserService {
    @Override
    public AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return (AppUser) userDetails;
    }
}
