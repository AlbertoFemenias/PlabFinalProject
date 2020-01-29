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
@Table(name = "MODEL")//, uniqueConstraints = {
//@UniqueConstraint(columnNames = {"brand_name"})})
public class Model {

    @Id @GeneratedValue
    @Column(name = "id")
    private int modelId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

    @Column(name = "model_name", nullable = false)//, unique = true)
    private String name;

    @Column(name = "type") //cabrio, coupe, wagon
    private String type;

    @Column(name = "hp")
    private int hp;

    @Column(name = "weight")
    private int weight;


    public Model() {}

    public int getId() {
        return modelId;
    }

    public void setId( int id ) {
        this.modelId = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Model Clone() {
        return this;
    }
}