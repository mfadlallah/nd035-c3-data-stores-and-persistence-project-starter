package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @NotNull
    @OneToOne(
            mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private PetType type;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String notes;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY) //many pets can belong to one owner
    @JoinColumn(name = "owner_id")  //map the join column in the owner table
    private Owner owner;

    @ManyToMany(mappedBy = "pets") //many pets can belong to many schedules
    private List<Schedule> schedules = new ArrayList<>();

    public Pet() {
    }

    public Pet(Long id) {
        this.id = id;
    }

    public Pet(Long id,
               String name,
               PetType type,
               LocalDate birthDate,
               String notes,
               Owner owner
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
    }

    public void addPetType(PetType petType) {
        petType.setPet(this);
        this.type = petType;
    }

    public void addOwner(Owner owner) {
        owner.addPet(this);
        this.owner = owner;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Owner getCustomer() {
        return owner;
    }

    public void setCustomer(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pet pet = (Pet) o;
        return Objects.equals(getId(), pet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
