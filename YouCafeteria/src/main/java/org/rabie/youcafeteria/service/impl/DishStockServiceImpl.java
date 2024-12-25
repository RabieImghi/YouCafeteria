package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.DishStockRepository;
import org.rabie.youcafeteria.service.DishStockService;
import org.springframework.stereotype.Component;

@Component
public class DishStockServiceImpl implements DishStockService {
    private final DishStockRepository dishStockRepository;

    public DishStockServiceImpl(DishStockRepository dishStockRepository) {
        this.dishStockRepository = dishStockRepository;
    }
}
