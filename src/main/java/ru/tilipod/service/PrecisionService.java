package ru.tilipod.service;

import ru.tilipod.jpa.entity.nneas.Course;

public interface PrecisionService {

    void addPrecisionForCourse(double precision, Course course);
}
