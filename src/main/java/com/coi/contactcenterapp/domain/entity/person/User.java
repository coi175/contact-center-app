package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.info.Role;
import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name="user_data")
public class User implements BaseEntity {
    @Id
    @Column(name="user_id")
    private Integer userId;
    @Column(name="username", unique = true, nullable = false)
    @NonNull
    private String username;
    @Column(name="password", nullable = false)
    @NonNull
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "role")
    @NonNull
    private Role role;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    @PrimaryKeyJoinColumn
    private Employee employee;
}
