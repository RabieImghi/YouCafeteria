package org.rabie.youcafeteria.repository;

import org.rabie.youcafeteria.domain.AppUser;
import org.rabie.youcafeteria.domain.Reservation;
import org.rabie.youcafeteria.domain.enums.DishType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByAppUserUsername(String username, PageRequest pageRequest);
    Page<Reservation> findByDishId(Long dishId, PageRequest pageRequest);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.appUser = :user AND r.dish.dishType = :dishType " +
            "AND r.reservationDate >= :startDate AND r.reservationDate < :endDate")
    long countByAppUserAndDishTypeAndDate(
            @Param("user") AppUser user,
            @Param("dishType") DishType dishType,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.appUser = :user AND r.dish.dishType = :dishType " +
            "AND r.reservationDate >= :startDate AND r.reservationDate < :endDate AND r.id <> :excludeId")
    long countByAppUserAndDishTypeAndDateExcludingId(
            @Param("user") AppUser user,
            @Param("dishType") DishType dishType,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("excludeId") Long excludeId);

}
