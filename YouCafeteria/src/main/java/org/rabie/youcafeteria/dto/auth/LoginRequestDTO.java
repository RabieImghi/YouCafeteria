package org.rabie.youcafeteria.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank(message = "username is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{5,}$",
            message = "Username must contain only letters and numbers, and must be at least 5 characters long"
    )
    private String username;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#%^&+=!]).{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character (@#%^&+=!), and be at least 8 characters long"
    )
    private String password;
}
