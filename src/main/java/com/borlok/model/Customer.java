package com.borlok.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CustomerId")
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Specialties",
    joinColumns = {@JoinColumn(name = "CustomerId")},
    inverseJoinColumns = {@JoinColumn(name = "SpecialtyId")})
    private Set<Specialty> specialties;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;

    public Customer() {
        specialties = new HashSet<>();
        account = new Account();
    }

    public Customer(Set<Specialty> specialties, Account account) {
        this();
        this.specialties = specialties;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", specialties=" + specialties +
                ", account=" + account +
                '}';
    }
}
