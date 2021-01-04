package com.borlok.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SpecialtyList")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SpecialtyId")
    private int id;
    @Column(name = "Specialty")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Specialties",
            joinColumns = {@JoinColumn(name = "SpecialtyId")},
            inverseJoinColumns = {@JoinColumn(name = "CustomerId")})
    private List<Customer> customers;

    public Specialty() {
        id = 0;
        name = "";
    }

    public Specialty(String name) {
        this();
        this.name = name;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return name;
    }
}
