package ru.tilipod.jpa.repository.nneas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.nneas.NeuronNetwork;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NeuronNetworkRepository extends JpaRepository<NeuronNetwork, Integer> {

    Optional<NeuronNetwork> findByTask_Id(Integer taskId);

    Optional<NeuronNetwork> findByTask_ProcessId(UUID processId);

}
