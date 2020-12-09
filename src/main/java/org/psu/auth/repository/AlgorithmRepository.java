package org.psu.auth.repository;

import org.psu.auth.model.enums.AlgorithmEnum;
import org.psu.auth.model.pojo.AlgorithmPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlgorithmRepository extends JpaRepository<AlgorithmPojo, UUID> {
    AlgorithmPojo findByAlgorithmName(AlgorithmEnum algorithmName);
}
