package org.rabie.youcafeteria.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.rabie.youcafeteria.domain.enums.Role;

@Getter
@Setter
public class UpdateUserDTO {
    @NotBlank(message = "username is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{5,}$",
            message = "Username must contain only letters and numbers, and must be at least 5 characters long"
    )
    private String username;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format"
    )
    private String email;
    @NotNull
    private Role role;
}
