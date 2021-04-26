package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
