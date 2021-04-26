package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Activity;
import com.udacity.jdnd.course3.critter.entity.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPet(Long id){
        return petRepository.findById(id).orElseThrow(PetNotFoundException::new);
    }

    public PetType createPetType(PetType petType){
        return petTypeRepository.save(petType);
    }

    public PetType getPetType(Long id){
        return petTypeRepository.findById(id).orElseThrow(PetTypeNotFoundException::new);
    }

    public Activity createPetBehaviour(Activity activity){
        return activityRepository.save(activity);
    }

    public List<Pet> getPets(Long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByIds(List<Long> ids) {
        return petRepository.findAllById(ids);
    }

    public List<Activity> getPetActivitiesByTypeId(Long petTypeId) {
        return activityRepository.findAllByPetTypeId(petTypeId);
    }
}
