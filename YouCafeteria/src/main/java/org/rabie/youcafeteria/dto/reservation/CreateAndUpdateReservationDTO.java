package org.rabie.youcafeteria.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAndUpdateReservationDTO {
    private Long id;
    private LocalDateTime reservationDate;
    private String dishName;
}
