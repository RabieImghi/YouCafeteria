package org.rabie.youcafeteria.service;

import org.rabie.youcafeteria.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Review createReview(Review review);
    Review updateReview(Review review);
    void deleteReview(Long id);
    Review getReview(Long id);
    Page<Review> getReviewsByDish(String dishName, int page, int size);
}
