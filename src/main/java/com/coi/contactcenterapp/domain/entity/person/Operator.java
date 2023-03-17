package com.coi.contactcenterapp.domain.entity.person;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="operator")
public class Operator implements BaseEntity {
    @Id
    @Column(name="operator_id")
    private Integer operatorId;
    @OneToOne(mappedBy = "operator", cascade = CascadeType.ALL, optional = false)
    @NonNull
    private Employee employee;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @OneToMany(mappedBy = "operator", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<Task> taskList;
    @OneToMany(mappedBy = "operator", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<PhoneCall> phoneCallList;
}
