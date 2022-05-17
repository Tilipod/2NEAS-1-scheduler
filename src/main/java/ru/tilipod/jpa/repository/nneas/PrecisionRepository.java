package ru.tilipod.jpa.repository.nneas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.nneas.Precision;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecisionRepository extends JpaRepository<Precision, Integer> {

    Optional<Precision> findTopByCourse_NeuronNetwork_TaskIdOrderByCreatedDateTimeDesc(Integer taskId);

    List<Precision> findAllByCourse_NeuronNetwork_TaskIdOrderByCreatedDateTimeAsc(Integer taskId);
}
