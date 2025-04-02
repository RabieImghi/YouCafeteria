package org.rabie.youcafeteria.repository;

import org.rabie.youcafeteria.domain.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findDishByName(String name);
    Page<Dish> findDishesByMenuName(String menuName, Pageable pageable);

}
