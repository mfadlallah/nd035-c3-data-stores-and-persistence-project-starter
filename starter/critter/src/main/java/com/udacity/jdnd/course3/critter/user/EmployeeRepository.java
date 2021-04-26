package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where :date member of e.daysAvailable")
    Optional<List<Employee>> getEmployees(@Param("date") DayOfWeek date);

    @Query("select e from Employee e INNER JOIN e.schedules s " +
            "WHERE :date member of e.daysAvailable AND :timeslot <= s.startTime OR :timeslot >= s.endTime")
    Optional<List<Employee>> getEmployeesInTimeSlot(@Param("date") DayOfWeek date, @Param("timeslot") LocalTime timeSlot);

    @Query("select e from Employee e where :date member of e.daysAvailable and e.id IN (:eids)")
    Optional<List<Employee>> getEmployeesByDateAndIds(@Param("date") DayOfWeek date, @Param("eids") List<Long> eids);
}
