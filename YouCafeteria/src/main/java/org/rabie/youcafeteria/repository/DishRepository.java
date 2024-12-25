package org.rabie.youcafeteria.repository;

import org.rabie.youcafeteria.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
