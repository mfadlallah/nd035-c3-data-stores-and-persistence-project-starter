package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Activity;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Owner getOwner(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }


    public Owner getOwnerByPetId(Long id) {
        return ownerRepository.findByPetsId(id).orElse(null);
    }


    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void updateEmployeeSchedule(Set<DayOfWeek> newSchdule, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            employee.setDaysAvailable(newSchdule);
            employeeRepository.save(employee);
        }
    }

    public List<Employee> getAvailableEmployees(LocalDate date, Set<EmployeeSkill> skills) {
        return employeeRepository.getEmployees(date.getDayOfWeek())
                .orElse(new ArrayList<>())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
        .collect(Collectors.toList());
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
}
