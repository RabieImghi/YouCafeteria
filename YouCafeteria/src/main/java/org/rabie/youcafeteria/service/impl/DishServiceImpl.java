package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.exception.exceptions.DishException;
import org.rabie.youcafeteria.repository.DishRepository;
import org.rabie.youcafeteria.service.DishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish addDish(Dish dish) {
        if(dish == null)
            throw new DishException("Dish is null", HttpStatus.BAD_REQUEST);
        if(this.getDish(dish.getName()) != null)
            throw new DishException("Dish already exists", HttpStatus.BAD_REQUEST);
        if(dish.getQuantity() < 5)
            throw new DishException("Dish quantity must be greater than 5", HttpStatus.BAD_REQUEST);
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Dish dish) {
        if(dish == null)
            throw new DishException("Dish is null", HttpStatus.BAD_REQUEST);
        Dish dish1 = this.getDish(dish.getName());
        if(dish1 == null)
            throw new DishException("Dish does not exist", HttpStatus.BAD_REQUEST);
        if(dish.getQuantity() < 1)
            throw new DishException("Dish quantity must be greater than 1", HttpStatus.BAD_REQUEST);
        dish.setId(dish1.getId());
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(String name) {
        Dish dish = this.getDish(name);
        if(dish == null)
            throw new DishException("Dish does not exist", HttpStatus.BAD_REQUEST);
        dishRepository.delete(dish);
    }

    @Override
    public Dish getDish(String name) {
        return dishRepository.findDishByName(name).orElse(null);
    }

    @Override
    public Page<Dish> getAllDishes(int page, int size) {
        return dishRepository.findAll(PageRequest.of(page, size));
    }


}
