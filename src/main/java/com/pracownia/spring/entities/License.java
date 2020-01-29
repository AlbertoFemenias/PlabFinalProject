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
@Table(name = "LICENSE")//, uniqueConstraints = {
//@UniqueConstraint(columnNames = {"brand_name"})})
public class License {

    @Id @GeneratedValue
    @Column(name = "id")
    private int licenseId;

    @Column(name = "number",length = 8)
    private int number;

    @Column(name = "expedition")
    private int expedition;

    @Column(name = "type")
    private String type;



    public License() {}

    public int getId() {
        return licenseId;
    }

    public void setId( int id ) {
        this.licenseId = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setExpedition(int expedition) {
        this.expedition = expedition;
    }
    public int getExpedition() {
        return expedition;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}