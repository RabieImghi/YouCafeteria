package org.rabie.youcafeteria.vm.stock;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StockResponseVM {
    private String name;
    private String description;
    private int quantity;
    private LocalDateTime creationDate;
}
