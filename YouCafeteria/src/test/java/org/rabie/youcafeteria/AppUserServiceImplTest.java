package org.rabie.youcafeteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.exception.exceptions.UserException;
import org.rabie.youcafeteria.repository.AppUserRepository;
import org.rabie.youcafeteria.service.impl.AppUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserServiceImpl appUserService;

    private AppUser user;

    @BeforeEach
    void setUp() {
        user = new AppUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
    }

    // 1. Test de sauvegarde d'un utilisateur
    @Test
    void saveAppUser_Success() {
        when(appUserRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(appUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser result = appUserService.saveAppUser(user);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(appUserRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void saveAppUser_Fail_EmailAlreadyExists() {
        when(appUserRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.saveAppUser(user)
        );

        assertEquals("Error : Email already exists", exception.getMessage());
    }

    @Test
    void saveAppUser_Fail_NullUser() {
        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.saveAppUser(null)
        );

        assertEquals("Error : User is null", exception.getMessage());
    }

    // 2. Test de récupération d'un utilisateur par username
    @Test
    void getUserByUsername_Success() {
        when(appUserRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        AppUser result = appUserService.getUserByUsername("testuser");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void getUserByUsername_Fail_UserNotFound() {
        when(appUserRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> appUserService.getUserByUsername("testuser")
        );

        assertEquals("User not found with username: testuser", exception.getMessage());
    }

    @Test
    void getUserByUsername_Fail_NullUsername() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> appUserService.getUserByUsername(null)
        );

        assertEquals("User not found with username: null", exception.getMessage());
    }

    // 3. Test de mise à jour d'un utilisateur
    @Test
    void updateAppUser_Success() {
        when(appUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser updatedUser = new AppUser();
        updatedUser.setUsername("testuser");
        updatedUser.setEmail("newemail@example.com");

        AppUser result = appUserService.updateAppUser(updatedUser);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void updateAppUser_Fail_UserNotFound() {
        when(appUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.updateAppUser(user)
        );

        assertEquals("Error : User not found", exception.getMessage());
    }

    @Test
    void updateAppUser_Fail_NullUser() {
        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.updateAppUser(null)
        );

        assertEquals("Error : User is null", exception.getMessage());
    }

    // 4. Test de mise à jour du profil
    @Test
    void updateProfile_Success() {
        when(appUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser result = appUserService.updateProfile(user);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void updateProfile_Fail_UserNotFound() {
        when(appUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.updateProfile(user)
        );

        assertEquals("Error : User not found", exception.getMessage());
    }

    @Test
    void updateProfile_Fail_NullUser() {
        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.updateProfile(null)
        );

        assertEquals("Error : User is null", exception.getMessage());
    }

    // 5. Test de suppression d'un utilisateur
    @Test
    void deleteAppUser_Success() {
        when(appUserRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> appUserService.deleteAppUser("testuser"));
        verify(appUserRepository, times(1)).delete(user);
    }

    @Test
    void deleteAppUser_Fail_UserNotFound() {
        when(appUserRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.deleteAppUser("testuser")
        );

        assertEquals("Error : User not found", exception.getMessage());
    }

    @Test
    void deleteAppUser_Fail_NullUsername() {
        UserException exception = assertThrows(
                UserException.class,
                () -> appUserService.deleteAppUser(null)
        );

        assertEquals("Error : User not found", exception.getMessage());
    }
}
