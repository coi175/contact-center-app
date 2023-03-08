package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @Column(name="employee_id")
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
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, optional = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Director director;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Manager manager;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Operator operator;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ActionLog> actionLogList;
}
