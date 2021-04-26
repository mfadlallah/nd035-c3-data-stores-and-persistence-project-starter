package com.udacity.jdnd.course3.critter.pet;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.entity.Owner;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Activity;
import com.udacity.jdnd.course3.critter.entity.PetType;
import com.udacity.jdnd.course3.critter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Owner owner = userService.getOwner(petDTO.getOwnerId());
        PetType type = petService.getPetType(petDTO.getTypeId());

        Pet pet = new Pet();
        pet.addOwner(owner);
        pet.addPetType(type);
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());

        return PetDTO.convertPetToPetDTO(petService.createPet(pet));
    }

    @PostMapping("/type")
    public PetTypeDTO savePetType(@RequestBody PetTypeDTO petTypeDTO) {
        PetType petType = new PetType();
        petType.setType(petTypeDTO.getType());
        return PetTypeDTO.convertPetTypeToPetTypeDTO(petService.createPetType(petType));
    }

    @JsonView(ActivityOnly.class)
    @PostMapping("/activity")
    public Activity savePetActivity(@RequestBody Activity petTypeDTO) {
        PetType type = petService.getPetType(petTypeDTO.getPetType().getId());

        Activity activity = new Activity();
        activity.setActivity(petTypeDTO.getActivity());
        activity.setPetType(type);

        return petService.createPetBehaviour(activity);
    }

    @JsonView(ActivityOnly.class)
    @GetMapping("/activity/{petTypeId}")
    public List<Activity> getPetTypeActivities(@PathVariable long petTypeId) {
        return petService.getPetActivitiesByTypeId(petTypeId);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        PetDTO petDTORes = PetDTO.convertPetToPetDTO(pet);
        petDTORes.setTypeId(pet.getType().getId());
        petDTORes.setOwnerId(pet.getOwner().getId());
        return petDTORes;
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService
                .getPets()
                .stream()
                .map(pet -> {
                    PetDTO petDTO = PetDTO.convertPetToPetDTO(pet);
                    petDTO.setOwnerId(pet.getOwner().getId());
                    petDTO.setTypeId(pet.getType().getId());
                    return petDTO;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService
                .getPets(ownerId)
                .stream()
                .map(pet -> {
                    PetDTO petDTO = PetDTO.convertPetToPetDTO(pet);
                    petDTO.setOwnerId(pet.getOwner().getId());
                    petDTO.setTypeId(pet.getType().getId());
                    return petDTO;
                })
                .collect(Collectors.toList());
    }
}
