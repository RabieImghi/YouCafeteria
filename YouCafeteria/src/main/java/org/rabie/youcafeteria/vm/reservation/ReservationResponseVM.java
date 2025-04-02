package org.rabie.youcafeteria.vm.reservation;

import lombok.Getter;
import lombok.Setter;
import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.vm.RegisterResponseVM;
import org.rabie.youcafeteria.vm.dish.DishResponse;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationResponseVM {
    private Long id;
    private LocalDateTime reservationDate;
    private AppUser user;
    private DishResponse dish;
}
