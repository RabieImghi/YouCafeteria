package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    AppUser getUserByUsername(String username);
    AppUser updateAppUser(AppUser appUser);
    void deleteAppUser(String username);
    Page<AppUser> getAllAppUsers(int page, int size);
}
