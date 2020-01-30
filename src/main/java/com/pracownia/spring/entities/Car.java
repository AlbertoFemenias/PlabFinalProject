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
@Table(name = "CAR")//, uniqueConstraints = {
//@UniqueConstraint(columnNames = {"brand_name"})})
public class Car {

    @Id @GeneratedValue
    @Column(name = "id")
    private int carId;

    @Column(name = "year")
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    private Model model;



    public Car() {}

    public int getId() {
        return carId;
    }

    public void setId( int id ) {
        this.carId = id;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public Model getModel() {
        return model;
    }
}