package com.org.cesar.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.cesar.restaurant.orm.Order;
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    
}
