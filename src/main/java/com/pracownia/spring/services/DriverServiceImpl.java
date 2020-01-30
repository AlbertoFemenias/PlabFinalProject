package com.pracownia.spring.services;

import com.pracownia.spring.entities.Driver;
import com.pracownia.spring.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Driver service implement.
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Iterable<Driver> listAllDriversPaging(Integer pageNr, Integer howManyOnPage) {
        return driverRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Driver> listAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(Integer id) {
        return driverRepository.findOne(id);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(Integer id) {
        driverRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (driverRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
