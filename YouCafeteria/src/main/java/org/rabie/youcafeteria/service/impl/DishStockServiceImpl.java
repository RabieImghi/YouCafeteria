package org.rabie.youcafeteria.service.impl;

import jakarta.transaction.Transactional;
import org.rabie.youcafeteria.domain.DishStock;
import org.rabie.youcafeteria.exception.exceptions.DishException;
import org.rabie.youcafeteria.repository.DishStockRepository;
import org.rabie.youcafeteria.service.DishStockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DishStockServiceImpl implements DishStockService {
    private final DishStockRepository dishStockRepository;

    public DishStockServiceImpl(DishStockRepository dishStockRepository) {
        this.dishStockRepository = dishStockRepository;
    }

    @Override
    public DishStock save(DishStock dishStock) {
        if(dishStock == null)
            throw new DishException("DishStock is null", HttpStatus.BAD_REQUEST);
        if(dishStock.getId() != null && this.findById(dishStock.getId()) != null)
            throw new DishException("DishStock already exists", HttpStatus.BAD_REQUEST);
        if(dishStock.getQuantity() < 1)
            throw new DishException("DishStock quantity must be greater than 1", HttpStatus.BAD_REQUEST);
        return dishStockRepository.save(dishStock);
    }

    @Override
    public DishStock update(DishStock dishStock) {
        if(dishStock == null)
            throw new DishException("DishStock is null", HttpStatus.BAD_REQUEST);
        DishStock dishStock1 = this.findById(dishStock.getId());
        if(dishStock1 == null)
            throw new DishException("DishStock does not exist", HttpStatus.BAD_REQUEST);
        if(dishStock.getQuantity() < 1)
            throw new DishException("DishStock quantity must be greater than 1", HttpStatus.BAD_REQUEST);
        dishStock.setId(dishStock1.getId());
        return dishStockRepository.save(dishStock);
    }

    @Override
    public void delete(Long id) {
        DishStock dishStock = this.findById(id);
        if(dishStock == null)
            throw new DishException("DishStock does not exist", HttpStatus.BAD_REQUEST);
        dishStockRepository.delete(dishStock);

    }

    @Override
    public DishStock findById(Long id) {
        return dishStockRepository.findById(id).orElse(null);
    }

    @Override
    public Page<DishStock> findAll(int page, int size) {
        return dishStockRepository.findAll(PageRequest.of(page, size));
    }
    @Transactional
    public void deleteDishStocksByDishId(Long id) {
        dishStockRepository.deleteDishStocksByDishId(id);
    }
}
