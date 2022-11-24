package com.org.cesar.restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.cesar.restaurant.orm.Plate;
@Repository
public interface PlateRepository extends PagingAndSortingRepository<Plate, Long> {

}
