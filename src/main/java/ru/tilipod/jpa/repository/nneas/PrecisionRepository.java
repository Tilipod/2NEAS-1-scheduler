package ru.tilipod.jpa.repository.nneas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.nneas.Precision;

import java.util.List;

@Repository
public interface PrecisionRepository extends JpaRepository<Precision, Integer> {

    List<Precision> findAllByCourseId(Integer courseId);
}
