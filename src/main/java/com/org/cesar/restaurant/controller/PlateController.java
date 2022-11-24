package com.org.cesar.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cesar.restaurant.orm.Category;
import com.org.cesar.restaurant.orm.Plate;
import com.org.cesar.restaurant.repository.CategoryRepository;
import com.org.cesar.restaurant.repository.PlateRepository;
@RestController
@RequestMapping("/plate")
public class PlateController {
    @Autowired
    private PlateRepository plateRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/create/{catId}")
    public ResponseEntity<Plate> create(@RequestBody Plate plate, @PathVariable(name = "catId") Long catId) {
        Optional<Category> maybeCategory = this.categoryRepository.findById(catId);
        if (maybeCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            plate.setCategory(maybeCategory.get());
            this.plateRepository.save(plate);
            return new ResponseEntity<Plate>(plate, HttpStatus.CREATED);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Plate> create(@RequestBody Plate plate) {
            this.plateRepository.save(plate);
            return new ResponseEntity<Plate>(plate, HttpStatus.CREATED);
    }

    @GetMapping("/plates")
    public Iterable<Plate> getPlates() {
        Iterable<Plate> plates = this.plateRepository.findAll();
        return plates;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
            @RequestBody Plate plate) {
        Optional<Plate> maybePlate = this.plateRepository.findById(id);
        if (maybePlate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Category category = maybePlate.get().getCategory();
            plate.setId(id);
            plate.setCategory(category);
            this.plateRepository.save(plate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        Optional<Plate> maybePlate = this.plateRepository.findById(id);
        if (maybePlate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.plateRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
