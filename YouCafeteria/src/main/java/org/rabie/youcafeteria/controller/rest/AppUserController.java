package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.AppUserMapper;
import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.dto.user.UpdateUserDTO;
import org.rabie.youcafeteria.exception.exceptions.UserException;
import org.rabie.youcafeteria.service.impl.AppUserServiceImpl;
import org.rabie.youcafeteria.service.impl.AuthenticationUserServiceImpl;
import org.rabie.youcafeteria.vm.RegisterResponseVM;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserServiceImpl appUserService;
    private final AppUserMapper appUserMapper;
    private final AuthenticationUserServiceImpl authenticationUserService;

    public AppUserController(AppUserServiceImpl appUserService, AppUserMapper appUserMapper, AuthenticationUserServiceImpl authenticationUserService) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
        this.authenticationUserService = authenticationUserService;
    }


    @GetMapping("")
    public Page<RegisterResponseVM> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        return appUserService.getAllAppUsers(page, size).map(appUserMapper::toRegisterResponseVM);
    }


    @PutMapping("update")
    public ResponseEntity<RegisterResponseVM>   update(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        AppUser appUser = appUserMapper.toAppUserFromUpdateDTO(updateUserDTO);
        return ResponseEntity.ok(appUserMapper.toRegisterResponseVM(appUserService.updateAppUser(appUser)));
    }

    @GetMapping("/{username}")
    public RegisterResponseVM get(@PathVariable String username) {
        RegisterResponseVM responseVM = appUserMapper.toRegisterResponseVM(appUserService.findByUsername(username));
        if(responseVM == null)
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        return responseVM;
    }


    @DeleteMapping("delete/{username}")
    public void delete(@PathVariable String username) {
        appUserService.deleteAppUser(username);
    }

    @PutMapping("update/profile")
    public ResponseEntity<RegisterResponseVM> updateProfile(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        AppUser AuthenticationUser = authenticationUserService.getAuthenticatedUser();
        AppUser appUser = appUserMapper.toAppUserFromUpdateDTO(updateUserDTO);
        if(!AuthenticationUser.getUsername().equals(appUser.getUsername()))
            throw new UserException("You are not allowed to update this user", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(appUserMapper.toRegisterResponseVM(appUserService.updateProfile(appUser)));
    }
}
