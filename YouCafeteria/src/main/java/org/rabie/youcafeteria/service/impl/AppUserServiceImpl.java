package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.exception.exceptions.UserException;
import org.rabie.youcafeteria.repository.AppUserRepository;
import org.rabie.youcafeteria.service.AppUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUserServiceImpl  implements AppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        if(appUser == null)
            throw new UserException("User is null", HttpStatus.BAD_REQUEST);
        if(this.findByUsername(appUser.getUsername()) != null)
            throw new UserException("User already exists", HttpStatus.BAD_REQUEST);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public AppUser findByUsername(String username){
        return appUserRepository.findByUsername(username).orElse(null);
    }
}
