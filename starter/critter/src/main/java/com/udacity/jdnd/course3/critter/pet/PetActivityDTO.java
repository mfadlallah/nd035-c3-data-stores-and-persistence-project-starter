package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Activity;
import org.springframework.beans.BeanUtils;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
public class PetActivityDTO {
    private long id;
    private String activity;
    private long petTypeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public long getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(long petTypeId) {
        this.petTypeId = petTypeId;
    }

    public static PetActivityDTO convertPetBehaviourToPetBehaviourDTO(Activity pet) {
        PetActivityDTO petDTO = new PetActivityDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setPetTypeId(pet.getPetType().getId());
        return petDTO;
    }
}
