package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "petType", cascade = CascadeType.ALL)
    private Set<Activity> activities;

    public PetType() {
    }

    public PetType(String type) {
        this.type = type;
    }

    public PetType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Set<Activity> getPetBehaviours() {
        return activities;
    }

    public void setPetBehaviours(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PetType petType = (PetType) o;
        return Objects.equals(getId(), petType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
