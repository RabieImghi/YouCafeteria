package org.rabie.youcafeteria.vm.dish;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.enums.DishType;
import org.rabie.youcafeteria.vm.menu.MenuResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
    private String name;
    private String description;
    private String image;
    private boolean available;
    private int quantity;
    private DishType dishType;
    private MenuResponse menu;

}
