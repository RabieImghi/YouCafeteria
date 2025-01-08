package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;

@Entity
public class DishStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;

    @ManyToOne
    private Dish dish;

    @ManyToOne
    private Stock stock;

    public DishStock(int quantity, Dish dish, Stock stock) {
        this.quantity = quantity;
        this.dish = dish;
        this.stock = stock;
    }

    public DishStock() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
