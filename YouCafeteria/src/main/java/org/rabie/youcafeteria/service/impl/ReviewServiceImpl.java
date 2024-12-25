package org.rabie.youcafeteria.service.impl;

import org.rabie.youcafeteria.repository.ReviewRepository;
import org.rabie.youcafeteria.service.ReviewService;
import org.springframework.stereotype.Component;

@Component
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
}
