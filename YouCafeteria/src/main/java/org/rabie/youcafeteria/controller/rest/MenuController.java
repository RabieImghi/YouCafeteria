package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.MenuMapper;
import org.rabie.youcafeteria.domain.Menu;
import org.rabie.youcafeteria.dto.menu.CreateAndUpdateMenu;
import org.rabie.youcafeteria.service.impl.MenuServiceImpl;
import org.rabie.youcafeteria.vm.menu.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuServiceImpl menuService;
    private final MenuMapper menuMapper;

    public MenuController(MenuServiceImpl menuService, MenuMapper menuMapper) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuResponse> createMenu(@Valid @RequestBody CreateAndUpdateMenu createAndUpdateMenu) {
        Menu menu = menuMapper.toMenuFromCreateAndUpdateDto(createAndUpdateMenu);
        Menu savedMenu = menuService.save(menu);
        return ResponseEntity.ok(menuMapper.fromMenuToResponse(savedMenu));
    }

    @PutMapping("/update")
    public ResponseEntity<MenuResponse> updateMenu(@Valid @RequestBody CreateAndUpdateMenu createAndUpdateMenu) {
        Menu menu = menuMapper.toMenuFromCreateAndUpdateDto(createAndUpdateMenu);
        Menu updatedMenu = menuService.update(menu);
        return ResponseEntity.ok(menuMapper.fromMenuToResponse(updatedMenu));
    }


        @DeleteMapping("/delete/{name}")
        public ResponseEntity<String> deleteMenu(@PathVariable String name) {
            menuService.delete(name);
            return ResponseEntity.ok("Menu deleted successfully");
        }

        @GetMapping("/find/{name}")
        public ResponseEntity<MenuResponse> findMenuByName(@PathVariable String name) {
            Menu menu = menuService.findByName(name);
            if (menu == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(menuMapper.fromMenuToResponse(menu));
        }


    @GetMapping("/all")
    public ResponseEntity<Page<MenuResponse>> findAllMenus(@RequestParam int page, @RequestParam int size) {
        Page<Menu> menus = menuService.findAll(page, size);
        Page<MenuResponse> menuResponses = menus.map(menuMapper::fromMenuToResponse);
        return ResponseEntity.ok(menuResponses);
    }


}
