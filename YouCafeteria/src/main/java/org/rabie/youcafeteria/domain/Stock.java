package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "stock")
    private List<DishStock> dishStocks;

    public Stock(String name, String description, int quantity, double price, LocalDateTime creationDate, List<DishStock> dishStocks) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.creationDate = creationDate;
        this.dishStocks = dishStocks;
    }

    public Stock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<DishStock> getDishStocks() {
        return dishStocks;
    }

    public void setDishStocks(List<DishStock> dishStocks) {
        this.dishStocks = dishStocks;
    }
}
