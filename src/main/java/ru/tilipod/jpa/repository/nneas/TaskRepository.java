package ru.tilipod.jpa.repository.nneas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.nneas.Task;
import ru.tilipod.jpa.entity.nneas.enums.TaskStatusEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByProcessId(UUID processId);

    List<Task> findAllByStatus(TaskStatusEnum status);

}
