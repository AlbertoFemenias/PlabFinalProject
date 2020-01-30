package com.pracownia.spring.services;

import com.pracownia.spring.entities.License;
import com.pracownia.spring.repositories.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * License service implement.
 */
@Service
public class LicenseServiceImpl implements LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Override
    public Iterable<License> listAllLicensesPaging(Integer pageNr, Integer howManyOnPage) {
        return licenseRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<License> listAllLicenses() {
        return licenseRepository.findAll();
    }

    @Override
    public License getLicenseById(Integer id) {
        return licenseRepository.findOne(id);
    }

    @Override
    public License saveLicense(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public void deleteLicense(Integer id) {
        licenseRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (licenseRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
