package com.borlok.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int id;

    @ManyToMany
    @JoinTable(name = "specialties",
    joinColumns = {@JoinColumn(name = "customer_id")},
    inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
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
