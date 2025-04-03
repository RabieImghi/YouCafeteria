package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.Stock;
import org.rabie.youcafeteria.exception.exceptions.StockException;
import org.rabie.youcafeteria.repository.StockRepository;
import org.rabie.youcafeteria.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock saveStock(Stock stock) {
        return save(stock, false);
    }

    @Override
    public Stock updateStock(Stock stock) {
        return save(stock, true);
    }

    public Stock save(Stock stock, boolean isUpdate) {
        if(stock == null)
            throw new StockException("Stock is null", HttpStatus.BAD_REQUEST);
        if(stock.getQuantity() <= 0)
            throw new StockException("Stock quantity is negative", HttpStatus.BAD_REQUEST);
        if(stock.getCreationDate().isBefore(LocalDateTime.now()))
            throw new StockException("Stock creation date is in the past", HttpStatus.BAD_REQUEST);
        if(this.getStockByName(stock.getName()) == null && isUpdate)
            throw new StockException("Stock does not exist", HttpStatus.BAD_REQUEST);
        if(this.getStockByName(stock.getName()) != null && !isUpdate)
            throw new StockException("Stock already exists", HttpStatus.BAD_REQUEST);
        if(isUpdate){
            Stock stock1 = getStockByName(stock.getName());
            stock.setId(stock1.getId());
        }
        return stockRepository.save(stock);
    }

    @Override
    public void deleteStock(String name) {
        Stock stock = getStockByName(name);
        if(stock == null)
            throw new StockException("Stock does not exist", HttpStatus.BAD_REQUEST);
        stockRepository.delete(stock);

    }

    @Override
    public Stock getStock(String name) {
        Stock stock =  getStockByName(name);
        if(stock == null)
            throw new StockException("Stock does not exist", HttpStatus.BAD_REQUEST);
        return stock;
    }

    @Override
    public Page<Stock> getAllStocks(int page, int size) {
        return stockRepository.findAll(PageRequest.of(page, size));
    }

    public Stock getStockByName(String name) {
        return stockRepository.findByName(name).orElse( null);
    }
}
