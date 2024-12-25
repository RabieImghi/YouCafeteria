package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.StockRepository;
import org.rabie.youcafeteria.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
}
