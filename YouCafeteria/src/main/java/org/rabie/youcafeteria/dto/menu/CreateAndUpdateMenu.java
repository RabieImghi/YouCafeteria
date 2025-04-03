package org.rabie.youcafeteria.dto.menu;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreateAndUpdateMenu {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime menuDate;
    @NotNull
    private boolean active;
    @NotNull
    private int quantity;
}
