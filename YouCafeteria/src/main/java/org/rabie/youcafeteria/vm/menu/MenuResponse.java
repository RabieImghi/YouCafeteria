package org.rabie.youcafeteria.vm.menu;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MenuResponse {
    private String name;
    private String description;
    private LocalDateTime menuDate;
    private boolean active;
    private int quantity;
}
