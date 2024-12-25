package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private int rating;
    private LocalDateTime reviewDate;

    @ManyToOne
    private Dish dish;

}
