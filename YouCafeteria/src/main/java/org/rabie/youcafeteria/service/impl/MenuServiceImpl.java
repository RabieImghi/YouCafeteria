package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.Menu;
import org.rabie.youcafeteria.exception.exceptions.MenuException;
import org.rabie.youcafeteria.repository.MenuRepository;
import org.rabie.youcafeteria.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu save(Menu menu) {
        if (menu == null)
            throw new MenuException("Menu is null", HttpStatus.BAD_REQUEST);
        if (this.findByName(menu.getName()) != null)
            throw new MenuException("Menu already exists", HttpStatus.BAD_REQUEST);
        if(menu.getQuantity() < 1)
            throw new MenuException("Menu quantity must be greater than 1", HttpStatus.BAD_REQUEST);
        if(menu.getMenuDate().isBefore(LocalDateTime.now()))
            throw new MenuException("Menu date must be greater than today", HttpStatus.BAD_REQUEST);
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        if (menu == null)
            throw new MenuException("Menu is null", HttpStatus.BAD_REQUEST);
        Menu menu1 = this.findByName(menu.getName());
        if (menu1 == null)
            throw new MenuException("Menu does not exist", HttpStatus.BAD_REQUEST);
        if(menu.getQuantity() < 1)
            throw new MenuException("Menu quantity must be greater than 1", HttpStatus.BAD_REQUEST);
        if(menu.getMenuDate().isBefore(LocalDateTime.now()))
            throw new MenuException("Menu date must be greater than today", HttpStatus.BAD_REQUEST);
        menu.setId(menu1.getId());
        return menuRepository.save(menu);
    }

    @Override
    public void delete(String name) {
        Menu menu = this.findByName(name);
        if (menu == null)
            throw new MenuException("Menu does not exist", HttpStatus.BAD_REQUEST);
        menuRepository.delete(menu);

    }

    @Override
    public Menu findByName(String name) {
        return menuRepository.findByName(name).orElse(null);
    }

    @Override
    public Page<Menu> findAll(int page, int size) {
        return menuRepository.findAll(PageRequest.of(page, size));
    }
}
