package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime reservationDate;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Dish dish;

}
