package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.Dish;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DishService {
    Dish addDish(Dish dish);
    Dish updateDish(Dish dish);
    void deleteDish(String name);
    Dish getDish(String name);
    Page<Dish> getAllDishes(int page, int size);
}
