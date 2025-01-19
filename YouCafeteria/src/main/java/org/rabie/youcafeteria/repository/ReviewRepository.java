package org.rabie.youcafeteria.repository;

import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByDish(Dish dish, Pageable pageable);
}
