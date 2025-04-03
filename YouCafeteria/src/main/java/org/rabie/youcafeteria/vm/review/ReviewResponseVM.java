package org.rabie.youcafeteria.vm.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseVM {
    private Long id;
    private String comment;
    private int rating;
    private String dishName;
    private String reviewDate;
}
