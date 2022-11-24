package com.org.cesar.restaurant.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.cesar.restaurant.orm.Category;
import com.org.cesar.restaurant.repository.CategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/form")
    public String getForm() {
        return "category";
    }

    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestParam String name, Model model) {
        Category category = new Category();
        category.setName(name);
        this.categoryRepository.save(category);

        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public Iterable<Category> getCategories() {
        Iterable<Category> categories = this.categoryRepository.findAll();
        return categories;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
            @RequestBody Category category) {
        Optional<Category> maybeCategory = this.categoryRepository.findById(id);
        if (maybeCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            category.setId(id);
            this.categoryRepository.save(category);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        Optional<Category> maybeCategory = this.categoryRepository.findById(id);
        if (maybeCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
