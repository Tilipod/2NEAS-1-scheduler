package ru.tilipod.service;

import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.nneas.Course;
import ru.tilipod.jpa.entity.nneas.NeuronNetwork;

import java.util.UUID;

public interface CourceService {

    Course createCourseFromClientRequest(TrainingRequestDto request, NeuronNetwork net);

    Course findByTaskId(Integer taskId);

    Course findByTaskProcessId(UUID processId);
}
