package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends User {
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "owner",
            cascade = CascadeType.ALL
    )
    private List<Pet> pets = new ArrayList<>();

    public Owner() {
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public Owner(String phoneNumber, List<Pet> pets) {
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }

    public Owner(Long id, String name, String phoneNumber, List<Pet> pets) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
