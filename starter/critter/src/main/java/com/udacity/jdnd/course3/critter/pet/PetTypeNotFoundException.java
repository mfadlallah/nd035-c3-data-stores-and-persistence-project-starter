package com.udacity.jdnd.course3.critter.pet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet Type not found")
public class PetTypeNotFoundException extends RuntimeException {

    public PetTypeNotFoundException() {
    }

    public PetTypeNotFoundException(String message) {
        super(message);
    }
}
