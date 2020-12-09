package org.psu.auth.repository;

import org.psu.auth.model.enums.RoleEnum;
import org.psu.auth.model.pojo.RolePojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RolePojo, UUID> {
    RolePojo findByRoleName(RoleEnum roleName);
}
