package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Activity;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Owner;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private ActivityRepository activityRepository;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Owner owner = new Owner();
        owner.setPhoneNumber(customerDTO.getPhoneNumber());
        owner.setName(customerDTO.getName());
        return CustomerDTO.convertOwnerToOwnerDTO(userService.createOwner(owner));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Owner> ownerList = userService.getAllOwners();
        return ownerList.stream()
                .map(owner -> {
                    List<Pet> pets = owner.getPets();
                    CustomerDTO customerDTO = CustomerDTO.convertOwnerToOwnerDTO(owner);
                    if (pets != null) {
                        customerDTO.setPetIds(pets.stream().map(Pet::getId)
                                .collect(Collectors.toList()));
                    }
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return CustomerDTO.convertOwnerToOwnerDTO(userService.getOwnerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setName(employeeDTO.getName());
        return EmployeeDTO.convertEmployeeToEmployeeDTO(userService.createEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeDTO.convertEmployeeToEmployeeDTO(userService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.updateEmployeeSchedule(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return userService
                .getAvailableEmployees(employeeDTO.getDate(), employeeDTO.getSkills())
                .stream().map(EmployeeDTO::convertEmployeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

}
