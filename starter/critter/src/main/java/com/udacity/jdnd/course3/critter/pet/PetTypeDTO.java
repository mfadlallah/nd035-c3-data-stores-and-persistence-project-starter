package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.PetType;
import org.springframework.beans.BeanUtils;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
public class PetTypeDTO {
    private long id;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static PetTypeDTO convertPetTypeToPetTypeDTO(PetType petType) {
        PetTypeDTO petDTO = new PetTypeDTO();
        BeanUtils.copyProperties(petType, petDTO);
        return petDTO;
    }
}
