package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.ReservationMapper;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Reservation;
import org.rabie.youcafeteria.dto.reservation.CreateAndUpdateReservationDTO;
import org.rabie.youcafeteria.service.ReservationService;
import org.rabie.youcafeteria.vm.reservation.ReservationResponseVM;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping("/create/{username}")
    public ResponseEntity<ReservationResponseVM> createReservation(
            @PathVariable String username,
            @Valid @RequestBody CreateAndUpdateReservationDTO dto) {

        Reservation reservation = new Reservation();
        reservation.setReservationDate(dto.getReservationDate());

        Dish dish = new Dish();
        dish.setName(dto.getDishName());
        reservation.setDish(dish);

        // ⬅️ Pass username to service
        Reservation createdReservation = reservationService.createReservation(reservation, username);

        return ResponseEntity.ok(reservationMapper.fromReservationToVM(createdReservation));
    }

    @PutMapping("/update")
    public ResponseEntity<ReservationResponseVM> updateReservation(@Valid @RequestBody CreateAndUpdateReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setId(dto.getId());
        Dish dish = new Dish();
        dish.setName(dto.getDishName());
        reservation.setDish(dish);
        Reservation updatedReservation = reservationService.updateReservation(reservation);
        return ResponseEntity.ok(reservationMapper.fromReservationToVM(updatedReservation));
    }


    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseVM> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(reservationMapper.fromReservationToVM(reservation));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ReservationResponseVM>> getAllReservations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        Page<Reservation> reservations = reservationService.getAllReservations(page, size);
        Page<ReservationResponseVM> responseVMs = reservations.map(reservationMapper::fromReservationToVM);
        return ResponseEntity.ok(responseVMs);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Page<ReservationResponseVM>> getReservationsByUser(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {

        Page<ReservationResponseVM> reservations = reservationService.getReservationsByUser(username, page, size)
                .map(reservationMapper::fromReservationToVM);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/dish/{dishId}")
    public ResponseEntity<Page<ReservationResponseVM>> getReservationsByDish(
            @PathVariable Long dishId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {

        Page<ReservationResponseVM> reservations = reservationService.getReservationsByDish(dishId, page, size)
                .map(reservationMapper::fromReservationToVM);
        return ResponseEntity.ok(reservations);
    }
}
