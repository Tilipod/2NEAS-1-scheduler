package ru.tilipod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {


}
