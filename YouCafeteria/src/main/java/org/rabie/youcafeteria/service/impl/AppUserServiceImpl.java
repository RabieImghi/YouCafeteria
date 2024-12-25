package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.AppUserRepository;
import org.rabie.youcafeteria.service.AppUserService;
import org.springframework.stereotype.Component;

@Component
public class AppUserServiceImpl  implements AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
}
