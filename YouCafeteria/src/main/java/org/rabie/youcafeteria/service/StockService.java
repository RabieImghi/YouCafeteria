package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.Stock;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface StockService {

    Stock saveStock(Stock stock);
    Stock updateStock(Stock stock);
    void deleteStock(String name);
    Stock getStock(String name);
    Page<Stock> getAllStocks(int page, int size);
    Stock save(Stock stock, boolean isUpdate);

}
