package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s from Schedule s INNER JOIN s.employees e WHERE e.id = :employeeId")
    List<Schedule> findAllByEmployeesId(Long employeeId);

    @Query("SELECT s from Schedule s INNER JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> findSchedulesByPetId(Long petId);

    List<Schedule> findAllByPetsOwnerId(Long ownerId);
}
