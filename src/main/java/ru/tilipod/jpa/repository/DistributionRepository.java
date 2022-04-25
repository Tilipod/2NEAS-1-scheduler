package ru.tilipod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.Distribution;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Integer> {

    Optional<Distribution> findByTask_Id(Integer taskId);

    Optional<Distribution> findByTask_ProcessId(UUID processId);
}
