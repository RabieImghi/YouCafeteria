package org.rabie.youcafeteria.dto.stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAndUpdateStockDTO {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @NotNull(message = "quantity is required")
    private int quantity;
    @NotNull(message = "creationDate is required")
    private LocalDateTime creationDate;
}
