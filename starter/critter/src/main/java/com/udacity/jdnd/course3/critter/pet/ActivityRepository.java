package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByPetTypeId(Long petTypeId);

    @Query("SELECT a from Activity a where a.id in (:activites)")
    Optional<List<Activity>> getActivitiesByIds(@Param("activites") List<Long> activites);
}
