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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "stock")
    private List<DishStock> dishStocks;

}