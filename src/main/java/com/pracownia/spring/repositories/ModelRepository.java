package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ModelRepository extends CrudRepository<Model, Integer>, PagingAndSortingRepository<Model, Integer> {

    Model findByModelId(String modelId);

    @Query("select count(*) from Model p where p.id = ?1")
    Integer checkIfExist(Integer id);
}
