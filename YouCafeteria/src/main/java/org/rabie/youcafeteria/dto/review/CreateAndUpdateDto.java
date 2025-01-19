package org.rabie.youcafeteria.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAndUpdateDto {
    private Long id;
    private String comment;
    private int rating;
    private String dishName;
}
