package org.rabie.youcafeteria.vm.dish;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rabie.youcafeteria.domain.enums.DishType;

@Getter
@Setter
public class DishResponse {
    private String name;
    private String description;
    private String image;
    private boolean available;
    private int quantity;
    private DishType dishType;
}
