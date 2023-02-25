package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.RefreshToken;
import com.coi.contactcenterapp.domain.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    private Integer userId;
    @Column(name="username", unique = true, nullable = false)
    @NonNull
    private String username;
    @Column(name="password", nullable = false)
    @NonNull
    private String password;
    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    @NonNull
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="employee_id")
    @ToString.Exclude
    private Employee employee;
}
