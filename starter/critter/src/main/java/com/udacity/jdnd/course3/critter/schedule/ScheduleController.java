package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws EmployeeNotFoundException {
        List<Employee> employees = userService.getAvailableEmployees(
                scheduleDTO.getEmployeeIds(),
                scheduleDTO.getDate(),
                scheduleDTO.getActivities()
        );
        if (employees.isEmpty()) throw new EmployeeNotFoundException();
        List<Pet> pets = petService.getPetsByIds(scheduleDTO.getPetIds());

        Schedule schedule = new Schedule();
        schedule.setSkills(scheduleDTO.getActivities());
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule.setDate(scheduleDTO.getDate());
        schedule.addAllEmployees(employees);
        schedule.addAllPets(pets);
        return ScheduleDTO.convertScheduleToScheduleDTO(scheduleService.saveSchedule(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService
                .getAllSchedules()
                .stream()
                .map((ScheduleDTO::convertScheduleToScheduleDTO))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService
                .getAllSchedulesByPet(petId)
                .stream()
                .map((ScheduleDTO::convertScheduleToScheduleDTO))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService
                .getAllSchedulesByEmployeeId(employeeId)
                .stream()
                .map((ScheduleDTO::convertScheduleToScheduleDTO))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService
                .getAllSchedulesByOwnerId(customerId)
                .stream()
                .map((ScheduleDTO::convertScheduleToScheduleDTO))
                .collect(Collectors.toList());
    }
}
