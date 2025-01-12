package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.youcafeteria.domain.enums.DishType;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String image;
    private boolean available;
    private int quantity;
    private DishType dishType;

    @OneToMany(mappedBy = "dish")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "dish")
    private List<DishStock> dishStocks;

    @OneToMany(mappedBy = "dish")
    private List<Review> reviews;

    @ManyToOne
    private Menu menu;
}
