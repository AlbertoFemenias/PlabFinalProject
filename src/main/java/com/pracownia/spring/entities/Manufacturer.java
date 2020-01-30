package com.pracownia.spring.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Product entity.
 */
@Entity
@Table(name = "MANUFACTURER")//, uniqueConstraints = {
//@UniqueConstraint(columnNames = {"brand_name"})})
public class Manufacturer {

    @Id @GeneratedValue
    @Column(name = "id")
    private int manufacturerId;

    @Column(name = "brand_name", nullable = false)//, unique = true)
    private String name;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<Model> models = new ArrayList<Model>();

    @Column(name = "country")
    private String country;

    @Column(name = "year")
    private int year;


    public Manufacturer() {}

    public int getId() {
        return manufacturerId;
    }

    public void setId( int id ) {
        this.manufacturerId = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    public void addModel(Model m){
        this.models.add(m);
    }

}