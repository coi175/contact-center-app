package com.coi.contactcenterapp.domain.entity.calling;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="task")
public class Task implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long taskId;
    @Column(name="task_description", nullable = false)
    @NonNull
    private String taskDescription;
    @Column(name="task_status", nullable = false)
    @NonNull
    private String taskStatus;
    @Column(name="start_date", nullable = false)
    @NonNull
    private LocalDateTime startDate;
    @Column(name="end_date")
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<PhoneCall> phoneCallList;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "contact_id")
    @NonNull
    private Contact contact;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "manager_id")
    //@NonNull
    private Manager manager;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "operator_id")
    //@NonNull
    private Operator operator;
}
