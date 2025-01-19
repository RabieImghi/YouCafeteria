package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.DishMapper;
import org.rabie.youcafeteria.controller.mapper.DishStockMapper;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.DishStock;
import org.rabie.youcafeteria.domain.Menu;
import org.rabie.youcafeteria.dto.dish.CreateAndUpdateDish;
import org.rabie.youcafeteria.dto.dish.CreateDishStock;
import org.rabie.youcafeteria.exception.exceptions.MenuException;
import org.rabie.youcafeteria.service.impl.DishServiceImpl;
import org.rabie.youcafeteria.service.impl.DishStockServiceImpl;
import org.rabie.youcafeteria.service.impl.MenuServiceImpl;
import org.rabie.youcafeteria.service.impl.StockServiceImpl;
import org.rabie.youcafeteria.vm.dish.DishResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dish")
public class DishController {
    private final DishServiceImpl dishService;
    private final StockServiceImpl stockService;
    private final DishStockServiceImpl dishStockService;
    private final DishMapper dishMapper;
    private final MenuServiceImpl menuService;

    public DishController(DishServiceImpl dishService, DishStockServiceImpl dishStockService, DishMapper dishMapper, StockServiceImpl stockService, MenuServiceImpl menuService) {
        this.dishService = dishService;
        this.dishStockService = dishStockService;
        this.dishMapper = dishMapper;
        this.stockService = stockService;
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public ResponseEntity<DishResponse> createDish(@Valid @RequestBody CreateAndUpdateDish createAndUpdateDish) {
        Dish dish = dishMapper.fromCreateAndUpdateDtoToDish(createAndUpdateDish);
        Menu menu = menuService.findByName(createAndUpdateDish.getMenuName());
        if(menu == null)
            throw new MenuException("Menu does not exist", HttpStatus.BAD_REQUEST);
        dish.setMenu(menu);
        Dish savedDish = dishService.addDish(dish);
        createAndUpdateDish.getStockNames().forEach(stockName -> {
            DishStock dishStock = new DishStock();
            dishStock.setDish(savedDish);
            dishStock.setStock(stockService.getStockByName(stockName.split("/")[0]));
            dishStock.setQuantity(Integer.parseInt(stockName.split("/")[1]));
            dishStockService.save(dishStock);
        });
        return ResponseEntity.ok(dishMapper.fromDishToResponse(savedDish));
    }
}
