package ru.tilipod.service;

import ru.tilipod.jpa.entity.nneas.Course;
import ru.tilipod.jpa.entity.nneas.Precision;

import java.util.List;

public interface PrecisionService {

    void addPrecisionForCourse(double precision, Course course);

    Precision findLastByTaskId(Integer taskId);

    List<Precision> findAllByTaskId(Integer taskId);
}
