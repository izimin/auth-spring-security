package org.psu.auth.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserPojo {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "userId", updatable = false, nullable = false)
    private UUID userId;
    private String login;
    private String email;
    private long phone;
    @ManyToOne(targetEntity = PhoneCodePojo.class)
    @JoinColumn(name = "phone_code_id")
    private PhoneCodePojo phoneCodeId;
    private String firstName;
    private String lastName;
    private String patronym;
    private String hash;
    private String salt;

    @ManyToOne(targetEntity = SystemPojo.class)
    @JoinColumn(name = "system_id")
    private SystemPojo systemId;

    @ManyToOne(targetEntity = AlgorithmPojo.class)
    @JoinColumn(name = "algorithm_id")
    private AlgorithmPojo algorithmId;

    @ManyToMany(targetEntity = RolePojo.class)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<RolePojo> roles;
}
