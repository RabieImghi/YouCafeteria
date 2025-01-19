package org.rabie.youcafeteria.vm.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationResponseVM {
    private Long id;
    private LocalDateTime reservationDate;
    private String username;
    private String dishName;
}
