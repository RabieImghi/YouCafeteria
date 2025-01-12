package org.rabie.youcafeteria.dto.dish;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rabie.youcafeteria.domain.enums.DishType;

@Getter
@Setter
public class CreateAndUpdateDish {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String image;
    @NotNull
    private boolean available;
    @NotNull
    private int quantity;
    @NotNull
    private DishType dishType;
    @NotNull
    private String menuName;
}
