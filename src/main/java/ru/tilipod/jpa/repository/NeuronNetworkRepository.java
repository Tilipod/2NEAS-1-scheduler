package ru.tilipod.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.NeuronNetwork;

@Repository
public interface NeuronNetworkRepository extends JpaRepository<NeuronNetwork, Integer> {


}
