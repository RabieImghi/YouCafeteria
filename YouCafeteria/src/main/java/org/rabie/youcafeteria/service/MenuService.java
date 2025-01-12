package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface MenuService {
    Menu save(Menu menu);
    Menu update(Menu menu);
    void delete(String name);
    Menu findByName(String name);
    Page<Menu> findAll(int page, int size);

}
