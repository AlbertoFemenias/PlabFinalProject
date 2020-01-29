package com.pracownia.spring.services;

import com.pracownia.spring.entities.Car;

public interface CarService {

    Iterable<Car> listAllCars();

    Car getCarById(Integer id);

    Car saveCar(Car car);

    void deleteCar(Integer id);

    Boolean checkIfExist(Integer id);

    public Iterable<Car> listAllCarsPaging(Integer pageNr, Integer howManyOnPage);

}
