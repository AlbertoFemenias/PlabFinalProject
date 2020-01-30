package com.pracownia.spring.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller {

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "seller_seq")
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @Column
    private String country;

    @ElementCollection
    @CollectionTable(name = "products")
    @Column(name = "product_id")
    private List<String> products = new ArrayList<>();

    @ManyToMany(mappedBy = "sellers")
    private List<Product> productsOb;

    //required by Hibernate
    public Seller(){

    }

    public Seller(String name, String country, List<String> products) {
        this.name = name;
        this.country = country;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
