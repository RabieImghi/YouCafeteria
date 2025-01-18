package org.rabie.youcafeteria.dto.dish;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Stock;

@Getter
@Setter
public class CreateDishStock {
    private int quantity;
    private String dishName;
    private String stockName;
}
