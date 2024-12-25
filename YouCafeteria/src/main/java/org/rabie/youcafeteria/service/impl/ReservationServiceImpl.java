package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.ReservationRepository;
import org.rabie.youcafeteria.service.ReservationService;
import org.springframework.stereotype.Component;

@Component
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
