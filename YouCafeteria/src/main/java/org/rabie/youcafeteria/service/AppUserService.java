package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
}
