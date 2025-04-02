package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.StockMapper;
import org.rabie.youcafeteria.domain.Stock;
import org.rabie.youcafeteria.dto.stock.CreateAndUpdateStockDTO;
import org.rabie.youcafeteria.service.impl.StockServiceImpl;
import org.rabie.youcafeteria.vm.stock.StockResponseVM;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockServiceImpl stockService;
    private final StockMapper stockMapper;

    public StockController(StockServiceImpl stockService, StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<StockResponseVM> createStock(@Valid @RequestBody CreateAndUpdateStockDTO createAndUpdateStockDTO) {
        Stock stock = stockMapper.toStockFromStockDTO(createAndUpdateStockDTO);
        Stock savedStock = stockService.saveStock(stock);
        return ResponseEntity.ok(stockMapper.toStockResponseVM(savedStock));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteStock(@PathVariable String name) {
        stockService.deleteStock(name);
        return ResponseEntity.ok("Stock deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<StockResponseVM> updateStock(@Valid @RequestBody CreateAndUpdateStockDTO createAndUpdateStockDTO) {
        Stock stock = stockMapper.toStockFromStockDTO(createAndUpdateStockDTO);
        Stock savedStock = stockService.updateStock(stock);
        return ResponseEntity.ok(stockMapper.toStockResponseVM(savedStock));
    }

    @GetMapping("/details/{name}")
    public ResponseEntity<StockResponseVM> getStock(@PathVariable String name) {
        Stock stock = stockService.getStock(name);
        return ResponseEntity.ok(stockMapper.toStockResponseVM(stock));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<StockResponseVM>> getAllStocks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size) {
        Page<Stock> stockList = stockService.getAllStocks(page, size);
        return ResponseEntity.ok(stockList.map(stockMapper::toStockResponseVM));
    }
}
