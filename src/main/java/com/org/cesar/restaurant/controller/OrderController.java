package com.org.cesar.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cesar.restaurant.orm.Order;
import com.org.cesar.restaurant.orm.Plate;
import com.org.cesar.restaurant.repository.OrderRepository;
import com.org.cesar.restaurant.repository.PlateRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private PlateRepository plateRepository;

    @GetMapping("/orders")
    public Iterable<Order> getOrders() {
        return this.orderRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder() {
        Order order = new Order();
        this.orderRepository.save(order);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @PutMapping("/addPlateToOrder/{idOrder}/{idPlate}")
    public ResponseEntity<Order> addPlateToOrder(@PathVariable Long idOrder, @PathVariable Long idPlate) {
            Optional<Order> maybeOrder = this.orderRepository.findById(idOrder);
            Optional<Plate> maybePlate = this.plateRepository.findById(idPlate);
            if (maybeOrder.isEmpty() || maybePlate.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Order order = maybeOrder.get();
                Plate plate = maybePlate.get();

                order.addPlate(plate);
                order.addTotal(plate.getPrice());
                this.orderRepository.save(order);

                return new ResponseEntity<Order>(order, HttpStatus.OK);
            }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Optional<Order> maybeOrder = this.orderRepository.findById(id);
        if (maybeOrder.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.orderRepository.deleteById(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }

    @DeleteMapping("/removePlate/{idOrder}/{idPlate}")
    public ResponseEntity<?> removePlate(@PathVariable Long idOrder, @PathVariable Long idPlate) {
        Optional<Order> maybeOrder = this.orderRepository.findById(idOrder);
        Optional<Plate> maybePlate = this.plateRepository.findById(idPlate);
        if (maybeOrder.isEmpty() || maybePlate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Order order = maybeOrder.get();
            Plate plate = maybePlate.get();

            order.removePlate(plate);
            order.lessTotal(plate.getPrice());
            this.orderRepository.save(order);

            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }
    }

}
