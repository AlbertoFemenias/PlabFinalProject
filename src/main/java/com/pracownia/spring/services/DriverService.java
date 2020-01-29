package com.pracownia.spring.services;

import com.pracownia.spring.entities.Driver;

public interface DriverService {

    Iterable<Driver> listAllDrivers();

    Driver getDriverById(Integer id);

    Driver saveDriver(Driver driver);

    void deleteDriver(Integer id);

    Boolean checkIfExist(Integer id);

    public Iterable<Driver> listAllDriversPaging(Integer pageNr, Integer howManyOnPage);

}
