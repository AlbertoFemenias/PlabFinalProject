package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Manufacturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Integer>, PagingAndSortingRepository<Manufacturer, Integer> {

    Manufacturer findByManufacturerId(String manufacturerId);

    @Query("select count(*) from Manufacturer p where p.id = ?1")
    Integer checkIfExist(Integer id);
}
