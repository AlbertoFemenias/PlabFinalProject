package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DriverRepository extends CrudRepository<Driver, Integer>, PagingAndSortingRepository<Driver, Integer> {

    Driver findByDriverId(String driverId);

    @Query("select count(*) from Driver p where p.id = ?1")
    Integer checkIfExist(Integer id);
}
