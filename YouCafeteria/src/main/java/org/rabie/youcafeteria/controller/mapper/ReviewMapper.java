package org.rabie.youcafeteria.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rabie.youcafeteria.domain.Review;
import org.rabie.youcafeteria.dto.review.CreateAndUpdateDto;
import org.rabie.youcafeteria.vm.review.ReviewResponseVM;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review fromDtoToReview(CreateAndUpdateDto dto);
    ReviewResponseVM fromReviewToVM(Review review);
}
