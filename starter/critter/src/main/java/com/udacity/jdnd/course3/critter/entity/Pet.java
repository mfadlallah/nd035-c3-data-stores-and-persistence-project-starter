package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @ManyToOne(fetch = FetchType.LAZY) //many employees can belong to one schedule
    @JoinColumn(name = "schedule_id")  //map the join column in the schedule table
    private Schedule schedule;

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
               Owner owner,
               Schedule schedule
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
        this.schedule = schedule;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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

    public void addType(PetType type) {
        type.setPet(this);
        this.type = type;
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
