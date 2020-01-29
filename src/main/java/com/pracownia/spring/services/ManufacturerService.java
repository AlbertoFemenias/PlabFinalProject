package com.pracownia.spring.services;

import com.pracownia.spring.entities.Manufacturer;

public interface ManufacturerService {

    Iterable<Manufacturer> listAllManufacturers();

    Manufacturer getManufacturerById(Integer id);

    Manufacturer saveManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Integer id);

    Boolean checkIfExist(Integer id);

    public Iterable<Manufacturer> listAllManufacturersPaging(Integer pageNr, Integer howManyOnPage);

}
