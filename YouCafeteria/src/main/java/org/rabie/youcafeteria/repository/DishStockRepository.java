package org.rabie.youcafeteria.repository;

import org.rabie.youcafeteria.domain.DishStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishStockRepository extends JpaRepository<DishStock, Long> {
}
