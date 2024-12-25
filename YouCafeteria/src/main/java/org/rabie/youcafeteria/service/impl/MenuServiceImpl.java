package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.MenuRepository;
import org.rabie.youcafeteria.service.MenuService;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
}
