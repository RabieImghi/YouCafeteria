package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    Reservation createReservation(Reservation reservation,String username);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(Long id);
    Reservation getReservation(Long id);
    Page<Reservation> getAllReservations(int page, int size);
        Page<Reservation> getReservationsByUser(String username, int page, int size);
        Page<Reservation> getReservationsByDish(Long dishId, int page, int size);
}
