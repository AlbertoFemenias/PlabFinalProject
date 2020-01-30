package com.pracownia.spring.services;

import com.pracownia.spring.entities.Manufacturer;
import com.pracownia.spring.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Manufacturer service implement.
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Iterable<Manufacturer> listAllManufacturersPaging(Integer pageNr, Integer howManyOnPage) {
        return manufacturerRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Manufacturer> listAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerById(Integer id) {
        return manufacturerRepository.findOne(id);
    }

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteManufacturer(Integer id) {
        manufacturerRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (manufacturerRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
