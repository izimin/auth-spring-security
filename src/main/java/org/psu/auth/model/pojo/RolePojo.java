package org.psu.auth.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.psu.auth.model.enums.RoleEnum;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class RolePojo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "role_id", updatable = false, nullable = false)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @ManyToOne(targetEntity = SystemPojo.class)
    @JoinColumn(name = "system_id")
    private SystemPojo systemId;
}
