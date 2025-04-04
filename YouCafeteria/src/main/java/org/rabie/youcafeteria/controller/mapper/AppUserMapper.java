package org.rabie.youcafeteria.controller.mapper;

import org.mapstruct.Mapper;
import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.dto.auth.RegisterRequestDTO;
import org.rabie.youcafeteria.dto.user.UpdateUserDTO;
import org.rabie.youcafeteria.vm.LoginResponseVM;
import org.rabie.youcafeteria.vm.RegisterResponseVM;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    RegisterResponseVM toRegisterResponseVM(AppUser appUser);
    AppUser toAppUserFromRegisterDTO(RegisterRequestDTO registerRequestDTO);
    LoginResponseVM toLoginResponseVM(String token);

    AppUser toAppUserFromUpdateDTO(UpdateUserDTO updateUserDTO);

}
