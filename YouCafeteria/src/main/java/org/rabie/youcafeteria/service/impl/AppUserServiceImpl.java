package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.exception.exceptions.UserException;
import org.rabie.youcafeteria.repository.AppUserRepository;
import org.rabie.youcafeteria.service.AppUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        return save(appUser,false);
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        if(appUser == null)
            throw new UserException("User is null", HttpStatus.BAD_REQUEST);
        AppUser appUserFromDb = this.findByUsername(appUser.getUsername());
        if(appUserFromDb == null)
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        appUser.setId(appUserFromDb.getId());
        if(Objects.equals(appUser.getUsername(), appUserFromDb.getUsername())){
            if(!Objects.equals(appUser.getEmail(), appUserFromDb.getEmail())){
                return save(appUser,true);
            }else return appUserRepository.save(appUser);

        }else {
            return save(appUser,true);
        }
    }


    public AppUser updateProfile(AppUser appUser){
        if(appUser == null)
            throw new UserException("User is null", HttpStatus.BAD_REQUEST);
        AppUser appUserFromDb = this.findByUsername(appUser.getUsername());
        if(appUserFromDb == null)
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        appUser.setId(appUserFromDb.getId());
        return appUserRepository.save(appUser);
    }
    private AppUser save(AppUser appUser,boolean isUpdate){
        if(this.findByEmail(appUser.getEmail()) != null)
            throw new UserException("Email already exists", HttpStatus.BAD_REQUEST);
        if(this.findByUsername(appUser.getUsername()) != null && !isUpdate)
            throw new UserException("User already exists", HttpStatus.BAD_REQUEST);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUser(String username) {
        AppUser appUser = this.findByUsername(username);
        if(appUser == null)
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        appUserRepository.delete(appUser);

    }

    @Override
    public Page<AppUser> getAllAppUsers(int page, int size) {
        return appUserRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public AppUser findByUsername(String username){
        return appUserRepository.findByUsername(username).orElse(null);
    }
    public AppUser findByEmail(String email){
        return appUserRepository.findByEmail(email).orElse(null);
    }
}
