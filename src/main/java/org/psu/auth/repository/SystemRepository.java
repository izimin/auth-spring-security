package org.psu.auth.repository;

import org.psu.auth.model.pojo.SystemPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemRepository extends JpaRepository<SystemPojo, UUID> {
    SystemPojo findBySystemName(String systemName);
}
