package org.rabie.youcafeteria.controller.mapper;

import org.mapstruct.Mapper;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.dto.dish.CreateAndUpdateDish;
import org.rabie.youcafeteria.vm.dish.DishResponse;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish fromCreateAndUpdateDtoToDish(CreateAndUpdateDish createAndUpdateDish);
    DishResponse fromDishToResponse(Dish dish);
}
