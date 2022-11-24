package com.org.cesar.restaurant.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable
    private List<Plate> plates;
    private BigDecimal total;
    private LocalDate date;

    public Order() {
        plates = new ArrayList<>();
        total = BigDecimal.ZERO;
        date = LocalDate.now();
    }

    public Order(Long id, List<Plate> plates, BigDecimal total, LocalDate date) {
        this.id = id;
        this.plates = plates;
        this.total = total;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addPlate(Plate plate) {
        this.plates.add(plate);
    }

    public void removePlate(Plate plate) {
        this.plates.remove(plate);
    }

    public void lessTotal(BigDecimal price) {
        this.total = this.total.subtract(price);
    }

    public void addTotal(BigDecimal price) {
        this.total = this.total.add(price);
    }

}
