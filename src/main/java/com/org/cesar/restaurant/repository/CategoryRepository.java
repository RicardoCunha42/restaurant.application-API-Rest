package com.org.cesar.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.cesar.restaurant.orm.Category;
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    
}
