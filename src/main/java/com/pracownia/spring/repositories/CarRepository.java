package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends CrudRepository<Car, Integer>, PagingAndSortingRepository<Car, Integer> {

    Car findByCarId(String carId);

    @Query("select count(*) from Car p where p.id = ?1")
    Integer checkIfExist(Integer id);
}
