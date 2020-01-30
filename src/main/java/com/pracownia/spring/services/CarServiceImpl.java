package com.pracownia.spring.services;

import com.pracownia.spring.entities.Car;
import com.pracownia.spring.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Car service implement.
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Iterable<Car> listAllCarsPaging(Integer pageNr, Integer howManyOnPage) {
        return carRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Car> listAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepository.findOne(id);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (carRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
