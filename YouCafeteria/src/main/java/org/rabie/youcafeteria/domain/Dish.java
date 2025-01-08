package org.rabie.youcafeteria.domain;
import jakarta.persistence.*;
import org.rabie.youcafeteria.domain.enums.DishType;

import java.util.List;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private boolean available;
    private int quantity;
    private DishType dishType;

    @OneToMany(mappedBy = "dish")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "dish")
    private List<DishStock> dishStocks;

    @OneToMany(mappedBy = "dish")
    private List<Review> reviews;

    @ManyToOne
    private Menu menu;

    public Dish(String name, String description, double price, String image, boolean available, int quantity, DishType dishType, List<Reservation> reservations, List<DishStock> dishStocks, List<Review> reviews, Menu menu) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.available = available;
        this.quantity = quantity;
        this.dishType = dishType;
        this.reservations = reservations;
        this.dishStocks = dishStocks;
        this.reviews = reviews;
        this.menu = menu;
    }

    public Dish() {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<DishStock> getDishStocks() {
        return dishStocks;
    }

    public void setDishStocks(List<DishStock> dishStocks) {
        this.dishStocks = dishStocks;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
