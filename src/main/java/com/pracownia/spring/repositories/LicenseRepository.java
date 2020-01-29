package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.License;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LicenseRepository extends CrudRepository<License, Integer>, PagingAndSortingRepository<License, Integer> {

    License findByLicenseId(String licenseId);

    @Query("select count(*) from License p where p.id = ?1")
    Integer checkIfExist(Integer id);
}
