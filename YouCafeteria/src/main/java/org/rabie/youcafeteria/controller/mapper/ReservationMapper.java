package org.rabie.youcafeteria.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rabie.youcafeteria.domain.Reservation;
import org.rabie.youcafeteria.dto.reservation.CreateAndUpdateReservationDTO;
import org.rabie.youcafeteria.vm.reservation.ReservationResponseVM;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponseVM fromReservationToVM(Reservation reservation);
}
