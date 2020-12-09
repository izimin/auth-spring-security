package org.psu.auth.repository;

import org.psu.auth.model.pojo.PhoneCodePojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneCodeRepository extends JpaRepository<PhoneCodePojo, UUID> {
    PhoneCodePojo findByPhoneCodeValue(int phoneCodeValue);
}
