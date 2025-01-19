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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return getDishResponseResponseEntity(createAndUpdateDish, savedDish);
    }

    @PutMapping("/update")
    public ResponseEntity<DishResponse> updateDish(@Valid @RequestBody CreateAndUpdateDish createAndUpdateDish) {
        Dish dish = dishMapper.fromCreateAndUpdateDtoToDish(createAndUpdateDish);
        Menu menu = menuService.findByName(createAndUpdateDish.getMenuName());
        if (menu == null)
            throw new MenuException("Menu does not exist", HttpStatus.BAD_REQUEST);
        dish.setMenu(menu);
        Dish updatedDish = dishService.updateDish(dish);
        dishStockService.deleteDishStocksByDishId(updatedDish.getId());
        return getDishResponseResponseEntity(createAndUpdateDish, updatedDish);
    }

    private ResponseEntity<DishResponse> getDishResponseResponseEntity(@RequestBody @Valid CreateAndUpdateDish createAndUpdateDish, Dish updatedDish) {
        createAndUpdateDish.getStockNames().forEach(stockName -> {
            DishStock dishStock = new DishStock();
            dishStock.setDish(updatedDish);
            dishStock.setStock(stockService.getStockByName(stockName.split("/")[0]));
            dishStock.setQuantity(Integer.parseInt(stockName.split("/")[1]));
            dishStockService.save(dishStock);
        });
        return ResponseEntity.ok(dishMapper.fromDishToResponse(updatedDish));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteDish(@PathVariable String name) {
        dishService.deleteDish(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<DishResponse> findDishByName(@PathVariable String name) {
        Dish dish = dishService.getDish(name);
        if (dish == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dishMapper.fromDishToResponse(dish));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DishResponse>> getAllDishes(@RequestParam int page, @RequestParam int size) {
        Page<Dish> dishes = dishService.getAllDishes(page, size);
        Page<DishResponse> dishResponses = dishes.map(dishMapper::fromDishToResponse);
        return ResponseEntity.ok(dishResponses);
    }


}
