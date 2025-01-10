package org.rabie.youcafeteria.controller.rest;

import org.rabie.youcafeteria.controller.mapper.AppUserMapper;
import org.rabie.youcafeteria.service.impl.AppUserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserServiceImpl appUserService;
    private final AppUserMapper appUserMapper;

    public AppUserController(AppUserServiceImpl appUserService, AppUserMapper appUserMapper) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
    }


    @GetMapping("")
    public void get() {

    }


    @PutMapping("update")
    public void update() {

    }



    @DeleteMapping("delete/{id}")
    public void delete() {

    }

}
