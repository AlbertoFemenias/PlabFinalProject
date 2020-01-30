package com.pracownia.spring.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Product entity.
 */
@Entity
@Table(name = "DRIVER")//, uniqueConstraints = {
//@UniqueConstraint(columnNames = {"brand_name"})})
public class Driver {

    @Id @GeneratedValue
    @Column(name = "id")
    private int driverId;

    @Column(name = "driver_name", nullable = false)//, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CAR_id", referencedColumnName = "id")
    private Car car;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="LICENSE_id", referencedColumnName = "id")
    private License license;



    public Driver() {}

    public int getId() {
        return driverId;
    }

    public void setId( int id ) {
        this.driverId = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public void setLicense(License license) {
        this.license = license;
    }
    public License getLicense() {
        return license;
    }
}