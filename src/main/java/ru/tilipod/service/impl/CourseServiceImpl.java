package ru.tilipod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.TrainingRequestDto;
import ru.tilipod.jpa.entity.Course;
import ru.tilipod.jpa.entity.NeuronNetwork;
import ru.tilipod.jpa.repository.CourseRepository;
import ru.tilipod.service.CourceService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourceService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Course createCourseFromClientRequest(TrainingRequestDto request, NeuronNetwork net) {
        Course course = new Course();

        course.setCountEpoch(request.getCountEpoch());
        course.setNeuronNetwork(net);
        course.setCurrentEpoch(0);

        course = courseRepository.save(course);

        log.info("По задаче {} в БД добавлена информация для обучения", net.getTask().getId());

        return course;
    }

    @Override
    @Transactional(readOnly = true)
    public Course findByTaskId(Integer taskId) {
        return courseRepository.findByNeuronNetwork_Task_Id(taskId)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Course findByTaskProcessId(UUID processId) {
        return courseRepository.findByNeuronNetwork_Task_ProcessId(processId)
                .orElse(null);
    }
}
