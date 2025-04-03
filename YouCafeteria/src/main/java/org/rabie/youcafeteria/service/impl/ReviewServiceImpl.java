package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Review;
import org.rabie.youcafeteria.exception.exceptions.ReviewException;
import org.rabie.youcafeteria.repository.ReviewRepository;
import org.rabie.youcafeteria.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final DishServiceImpl dishService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, DishServiceImpl dishService) {
        this.reviewRepository = reviewRepository;
        this.dishService = dishService;
    }

    @Override
    public Review createReview(Review review) {
        if (review == null || review.getDish() == null) {
            throw new ReviewException("Review or associated dish is null", HttpStatus.BAD_REQUEST);
        }
        review.setReviewDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        if (review == null || review.getId() == null) {
            throw new ReviewException("Review or ID is null", HttpStatus.BAD_REQUEST);
        }
        Review existingReview = reviewRepository.findById(review.getId())
                .orElseThrow(() -> new ReviewException("Review not found", HttpStatus.NOT_FOUND));
        existingReview.setComment(review.getComment());
        existingReview.setRating(review.getRating());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewException("Review not found", HttpStatus.NOT_FOUND));
        reviewRepository.delete(review);
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewException("Review not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Review> getReviewsByDish(String dishName, int page, int size) {
        Dish dish = dishService.getDish(dishName);
        return reviewRepository.findAllByDish(dish, PageRequest.of(page, size));
    }
}
