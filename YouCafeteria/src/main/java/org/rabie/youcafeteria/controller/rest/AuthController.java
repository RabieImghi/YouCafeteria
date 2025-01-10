package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.AppUserMapper;
import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.dto.auth.LoginRequestDTO;
import org.rabie.youcafeteria.dto.auth.RegisterRequestDTO;
import org.rabie.youcafeteria.service.impl.AppUserServiceImpl;
import org.rabie.youcafeteria.util.JwtUtil;
import org.rabie.youcafeteria.vm.LoginResponseVM;
import org.rabie.youcafeteria.vm.RegisterResponseVM;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AppUserServiceImpl appUserService;
    private final AppUserMapper appUserMapper;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            AppUserServiceImpl appUserService,
            AppUserMapper appUserMapper
    ){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
    }
    @GetMapping("/test")
    public String test(){
        return "Test";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseVM> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        AppUser appUser = appUserMapper.toAppUserFromRegisterDTO(registerRequestDTO);
        appUser = appUserService.saveAppUser(appUser);
        return ResponseEntity.ok(appUserMapper.toRegisterResponseVM(appUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseVM> login(@RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );
        final UserDetails userDetails = appUserService.loadUserByUsername(loginRequestDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(appUserMapper.toLoginResponseVM(jwt));
    }
}
