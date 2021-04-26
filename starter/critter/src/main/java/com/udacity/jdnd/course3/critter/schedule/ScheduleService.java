package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAllSchedulesByEmployeeId(Long id) {
        return scheduleRepository.findAllByEmployeesId(id);
    }

    public List<Schedule> getAllSchedulesByPet(Long id) {
        return scheduleRepository.findSchedulesByPetId(id);
    }

    public List<Schedule> getAllSchedulesByOwnerId(Long id) {
        return scheduleRepository.findAllByPetsOwnerId(id);
    }

}
