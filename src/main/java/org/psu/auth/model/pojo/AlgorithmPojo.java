package org.psu.auth.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.psu.auth.model.enums.AlgorithmEnum;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "algorithms")
public class AlgorithmPojo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "algorithmId", updatable = false, nullable = false)
    private UUID algorithmId;
    @Enumerated(EnumType.STRING)
    private AlgorithmEnum algorithmName;

    @ManyToOne(targetEntity = SystemPojo.class)
    @JoinColumn(name = "system_id")
    private SystemPojo systemId;
}
