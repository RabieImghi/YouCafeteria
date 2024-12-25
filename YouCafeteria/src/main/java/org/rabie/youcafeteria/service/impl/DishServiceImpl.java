package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.DishRepository;
import org.rabie.youcafeteria.service.DishService;
import org.springframework.stereotype.Component;

@Component
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
}
