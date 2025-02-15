package org.rabie.youcafeteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Reservation;
import org.rabie.youcafeteria.domain.enums.DishType;
import org.rabie.youcafeteria.exception.exceptions.ReservationException;
import org.rabie.youcafeteria.repository.ReservationRepository;
import org.rabie.youcafeteria.service.impl.AppUserServiceImpl;
import org.rabie.youcafeteria.service.impl.DishServiceImpl;
import org.rabie.youcafeteria.service.impl.ReservationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private AppUserServiceImpl appUserService;

    @Mock
    private DishServiceImpl dishService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private AppUser user;
    private Dish dish;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        user = new AppUser();
        user.setUsername("testuser");

        dish = new Dish();
        dish.setName("Pasta");
        dish.setDishType(DishType.PRINCIPAL);

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setAppUser(user);
        reservation.setDish(dish);
        reservation.setReservationDate(LocalDateTime.now().plusDays(1));

        // Mock authentication
        UserDetails userDetails = new User("testuser", "password", Collections.emptyList());
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // 1. Test de création de réservation
    @Test
    void createReservation_Success() {
        when(appUserService.getUserByUsername("testuser")).thenReturn(user);
        when(dishService.getDish("Pasta")).thenReturn(dish);
        when(reservationRepository.countByAppUserAndDishTypeAndDate(any(), any(), any(), any())).thenReturn(0L);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.createReservation(reservation);

        assertNotNull(result);
        assertEquals("Pasta", result.getDish().getName());
    }

    // 2. Test de mise à jour de réservation
    @Test
    void updateReservation_Success() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(dishService.getDish("Pasta")).thenReturn(dish);
        when(reservationRepository.countByAppUserAndDishTypeAndDateExcludingId(any(), any(), any(), any(), anyLong())).thenReturn(0L);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation updatedReservation = new Reservation();
        updatedReservation.setId(1L);
        updatedReservation.setDish(dish);
        updatedReservation.setReservationDate(LocalDateTime.now().plusDays(2));

        Reservation result = reservationService.updateReservation(updatedReservation);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updateReservation_Fail_NotAuthorized() {
        AppUser anotherUser = new AppUser();
        anotherUser.setUsername("otheruser");
        reservation.setAppUser(anotherUser);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationException exception = assertThrows(
                ReservationException.class,
                () -> reservationService.updateReservation(reservation)
        );

        assertEquals("Error : You are not authorized to update this reservation", exception.getMessage());
    }

    // 3. Test d'annulation de réservation
    @Test
    void cancelReservation_Success() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        assertDoesNotThrow(() -> reservationService.cancelReservation(1L));
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void cancelReservation_Fail_NotAuthorized() {
        AppUser anotherUser = new AppUser();
        anotherUser.setUsername("otheruser");
        reservation.setAppUser(anotherUser);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationException exception = assertThrows(
                ReservationException.class,
                () -> reservationService.cancelReservation(1L)
        );

        assertEquals("Error : You are not authorized to cancel this reservation", exception.getMessage());
    }

}
