package org.rabie.youcafeteria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.rabie.youcafeteria.domain.enums.Role;


public class RegisterRequestDTO {
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
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character (@#%^&+=!), and be at least 6 characters long"
    )
    private String password;
    @NotBlank(message = "email is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format"
    )
    private String email;
    @NonNull
    private Role role;

    public @NotBlank(message = "username is required") @Pattern(
            regexp = "^[a-zA-Z0-9]{5,}$",
            message = "Username must contain only letters and numbers, and must be at least 5 characters long"
    ) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "username is required") @Pattern(
            regexp = "^[a-zA-Z0-9]{5,}$",
            message = "Username must contain only letters and numbers, and must be at least 5 characters long"
    ) String username) {
        this.username = username;
    }

    public @NotBlank(message = "firstName is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "firstName is required") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "lastName is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "lastName is required") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Password is required") @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character (@#%^&+=!), and be at least 6 characters long"
    ) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character (@#%^&+=!), and be at least 6 characters long"
    ) String password) {
        this.password = password;
    }

    public @NotBlank(message = "email is required") @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format"
    ) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "email is required") @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format"
    ) String email) {
        this.email = email;
    }

    public @NonNull Role getRole() {
        return role;
    }

    public void setRole(@NonNull Role role) {
        this.role = role;
    }
}
