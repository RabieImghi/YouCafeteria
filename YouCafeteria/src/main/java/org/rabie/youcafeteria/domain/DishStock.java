package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;

    @ManyToOne
    private Dish dish;

    @ManyToOne
    private Stock stock;

}
