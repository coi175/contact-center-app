package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.info.Role;
import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="user_data")
public class User implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    private Employee employee;
}
