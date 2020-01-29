package com.pracownia.spring.services;

import com.pracownia.spring.entities.License;

public interface LicenseService {

    Iterable<License> listAllLicenses();

    License getLicenseById(Integer id);

    License saveLicense(License license);

    void deleteLicense(Integer id);

    Boolean checkIfExist(Integer id);

    public Iterable<License> listAllLicensesPaging(Integer pageNr, Integer howManyOnPage);

}
