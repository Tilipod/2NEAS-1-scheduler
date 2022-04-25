package ru.tilipod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.Course;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByNeuronNetwork_Task_Id(Integer taskId);

    Optional<Course> findByNeuronNetwork_Task_ProcessId(UUID processId);

}
