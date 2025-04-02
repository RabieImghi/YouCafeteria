package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Reservation;
import org.rabie.youcafeteria.domain.enums.DishType;
import org.rabie.youcafeteria.exception.exceptions.ReservationException;
import org.rabie.youcafeteria.repository.ReservationRepository;
import org.rabie.youcafeteria.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final AppUserServiceImpl appUserService;
    private final DishServiceImpl dishService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, AppUserServiceImpl appUserService, DishServiceImpl dishService) {
        this.reservationRepository = reservationRepository;
        this.appUserService = appUserService;
        this.dishService = dishService;
    }

    @Override
    public Reservation createReservation(Reservation reservation, String username) {
        if (reservation == null) {
            throw new ReservationException("Reservation is null", HttpStatus.BAD_REQUEST);
        }
        if (reservation.getReservationDate().isBefore(LocalDateTime.now())) {
            throw new ReservationException("Reservation date must be in the future", HttpStatus.BAD_REQUEST);
        }
        AppUser user = appUserService.getUserByUsername(username);
        Dish dish = dishService.getDish(reservation.getDish().getName());
        LocalDateTime reservationDate = reservation.getReservationDate().toLocalDate().atStartOfDay();
        LocalDateTime nextDay = reservationDate.plusDays(1);
        long principalReservations = reservationRepository.countByAppUserAndDishTypeAndDate(
                user, DishType.PRINCIPAL, reservationDate, nextDay);
        long dessertReservations = reservationRepository.countByAppUserAndDishTypeAndDate(
                user, DishType.DESERT, reservationDate, nextDay);

        if (dish.getDishType() == DishType.PRINCIPAL && principalReservations > 0) {
            throw new ReservationException("You can only reserve one dish of type PRINCIPAL for this date", HttpStatus.BAD_REQUEST);
        }

        if (dish.getDishType() == DishType.DESERT && dessertReservations >= 2) {
            throw new ReservationException("You can only reserve up to two dishes of type DESSERT for this date", HttpStatus.BAD_REQUEST);
        }
        reservation.setAppUser(user);
        reservation.setDish(dish);

        return reservationRepository.save(reservation);
    }



    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (reservation == null || reservation.getId() == null) {
            throw new ReservationException("Reservation or ID is null", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Reservation existingReservation = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new ReservationException("Reservation not found", HttpStatus.NOT_FOUND));
        if (!existingReservation.getAppUser().getUsername().equals(username)) {
            throw new ReservationException("You are not authorized to update this reservation", HttpStatus.FORBIDDEN);
        }
        Dish dish = dishService.getDish(reservation.getDish().getName());
        LocalDateTime reservationDate = reservation.getReservationDate().toLocalDate().atStartOfDay();
        LocalDateTime nextDay = reservationDate.plusDays(1);
        long principalReservations = reservationRepository.countByAppUserAndDishTypeAndDateExcludingId(
                existingReservation.getAppUser(), DishType.PRINCIPAL, reservationDate, nextDay, existingReservation.getId());
        long dessertReservations = reservationRepository.countByAppUserAndDishTypeAndDateExcludingId(
                existingReservation.getAppUser(), DishType.DESERT, reservationDate, nextDay, existingReservation.getId());

        if (dish.getDishType() == DishType.PRINCIPAL && principalReservations > 0) {
            throw new ReservationException("You can only reserve one dish of type PRINCIPAL for this date", HttpStatus.BAD_REQUEST);
        }

        if (dish.getDishType() == DishType.DESERT && dessertReservations >= 2) {
            throw new ReservationException("You can only reserve up to two dishes of type DESSERT for this date", HttpStatus.BAD_REQUEST);
        }
        existingReservation.setReservationDate(reservation.getReservationDate());
        existingReservation.setDish(dish);

        return reservationRepository.save(existingReservation);
    }


    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException("Reservation not found", HttpStatus.NOT_FOUND));
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationException("Reservation not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Reservation> getAllReservations(int page, int size) {
        return reservationRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Reservation> getReservationsByUser(String username, int page, int size) {
        return reservationRepository.findByAppUserUsername(username, PageRequest.of(page, size));
    }

    @Override
    public Page<Reservation> getReservationsByDish(Long dishId, int page, int size) {
        return reservationRepository.findByDishId(dishId, PageRequest.of(page, size));
    }
}
