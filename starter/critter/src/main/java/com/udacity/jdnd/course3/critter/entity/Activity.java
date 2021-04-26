package com.udacity.jdnd.course3.critter.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.pet.ActivityOnly;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(ActivityOnly.class)
    private String activity;

    @ManyToOne(fetch = FetchType.LAZY) //many behaviors can belong to one PetType
    @JoinColumn(name = "pet_type_id")  //map the join column in the pet Type table
    private PetType petType;

    public Activity() {
    }

    public Activity(Long id) {
        this.id = id;
    }

    public Activity(String activity) {
        this.activity = activity;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPet(PetType PetType) {
        this.petType = PetType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Activity activity = (Activity) o;
        return Objects.equals(getId(), activity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
