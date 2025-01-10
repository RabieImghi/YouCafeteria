package org.rabie.youcafeteria.vm;

import lombok.Getter;
import lombok.Setter;
import org.rabie.youcafeteria.domain.enums.Role;

@Getter
@Setter
public class RegisterResponseVM {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Role role;
}
