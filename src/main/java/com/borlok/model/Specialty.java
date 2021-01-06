package com.borlok.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialty_list")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "specialty_id")
    private int id;
    @Column(name = "specialty")
    private String name;
    @ManyToMany
    @JoinTable(name = "specialties",
            joinColumns = {@JoinColumn(name = "specialty_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
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
