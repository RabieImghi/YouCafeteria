package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.DishStock;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DishStockService {
    DishStock save(DishStock dishStock);
    DishStock update(DishStock dishStock);
    void delete(Long id);
    DishStock findById(Long id);
    Page<DishStock> findAll(int page, int size);
}
