package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime menuDate;
    private boolean active;
    private int quantity;

    @OneToMany(mappedBy = "menu")
    private List<Dish> dishes;

}
