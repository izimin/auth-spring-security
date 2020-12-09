package org.psu.auth.repository;

import org.psu.auth.model.pojo.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserPojo, UUID> {
    UserPojo findByLogin(String login);
    UserPojo findByPhone(long phone);
    UserPojo findByEmail(String email);
}
