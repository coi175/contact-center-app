package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="employee")
public class Employee implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(name = "first_name", nullable = false)
    @NonNull
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lastName;
    @Column(name = "email", nullable = false)
    @NonNull
    private String email;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="director_id")
    private Director director;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="manager_id")
    private Manager manager;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="operator_id")
    private Operator operator;
}
