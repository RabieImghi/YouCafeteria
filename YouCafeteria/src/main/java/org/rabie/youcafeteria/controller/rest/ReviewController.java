package org.rabie.youcafeteria.controller.rest;

import jakarta.validation.Valid;
import org.rabie.youcafeteria.controller.mapper.ReviewMapper;
import org.rabie.youcafeteria.domain.Dish;
import org.rabie.youcafeteria.domain.Review;
import org.rabie.youcafeteria.dto.review.CreateAndUpdateDto;
import org.rabie.youcafeteria.service.ReviewService;
import org.rabie.youcafeteria.service.impl.DishServiceImpl;
import org.rabie.youcafeteria.vm.review.ReviewResponseVM;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final DishServiceImpl dishService;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper , DishServiceImpl dishService) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.dishService = dishService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewResponseVM> createReview(@Valid @RequestBody CreateAndUpdateDto dto) {
        Review review = reviewMapper.fromDtoToReview(dto);
        Dish  dish = dishService.getDish(dto.getDishName());
        review.setDish(dish);
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(reviewMapper.fromReviewToVM(createdReview));
    }

    @PutMapping("/update")
    public ResponseEntity<ReviewResponseVM> updateReview(@Valid @RequestBody CreateAndUpdateDto dto) {
        Review review = reviewMapper.fromDtoToReview(dto);
        Review updatedReview = reviewService.updateReview(review);
        return ResponseEntity.ok(reviewMapper.fromReviewToVM(updatedReview));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseVM> getReview(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        return ResponseEntity.ok(reviewMapper.fromReviewToVM(review));
    }

    @GetMapping("/dish/{dishName}")
    public ResponseEntity<Page<ReviewResponseVM>> getReviewsByDish(
            @PathVariable String dishName, @RequestParam int page, @RequestParam int size) {
        Page<ReviewResponseVM> reviews = reviewService.getReviewsByDish(dishName, page, size)
                .map(reviewMapper::fromReviewToVM);
        return ResponseEntity.ok(reviews);
    }
}
