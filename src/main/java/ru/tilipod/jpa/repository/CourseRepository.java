package ru.tilipod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


}
