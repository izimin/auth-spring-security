package org.psu.auth.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "phone_codes")
public class PhoneCodePojo implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "phoneCodeId", updatable = false, nullable = false)
    private UUID phoneCodeId;
    private int phoneCodeValue;

    @ManyToOne(targetEntity = SystemPojo.class)
    @JoinColumn(name = "system_id")
    private SystemPojo systemId;
}
