package ru.tilipod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.jpa.entity.nneas.Course;
import ru.tilipod.jpa.entity.nneas.Precision;
import ru.tilipod.jpa.repository.nneas.PrecisionRepository;
import ru.tilipod.service.PrecisionService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrecisionServiceImpl implements PrecisionService {

    private final PrecisionRepository precisionRepository;

    @Override
    @Transactional
    public void addPrecisionForCourse(double precision, Course course) {
        Precision entity = new Precision();

        entity.setPrecision(precision);
        entity.setCourse(course);

        precisionRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Precision findLastByTaskId(Integer taskId) {
        return precisionRepository.findTopByCourse_NeuronNetwork_TaskIdOrderByCreatedDateTimeDesc(taskId)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Precision> findAllByTaskId(Integer taskId) {
        return precisionRepository.findAllByCourse_NeuronNetwork_TaskIdOrderByCreatedDateTimeAsc(taskId);
    }
}
