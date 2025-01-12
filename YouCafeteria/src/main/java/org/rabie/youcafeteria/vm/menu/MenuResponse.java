package org.rabie.youcafeteria.vm.menu;

import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.vm.dish.DishResponse;

import java.time.LocalDateTime;
import java.util.List;

public class MenuResponse {
    private String name;
    private String description;
    private LocalDateTime menuDate;
    private boolean active;
    private int quantity;
    private List<DishResponse> dishes;
}
