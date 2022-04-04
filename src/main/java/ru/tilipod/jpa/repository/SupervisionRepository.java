package ru.tilipod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.Supervision;

@Repository
public interface SupervisionRepository extends JpaRepository<Supervision, Integer> {


}
