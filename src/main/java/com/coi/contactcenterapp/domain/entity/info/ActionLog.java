package com.coi.contactcenterapp.domain.entity.info;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="action_log")
public class ActionLog implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionLogId;
    @Column(name = "log_message", nullable = false)
    @NonNull
    private String logMessage;
    @Column(name = "log_date_time", nullable = false)
    @NonNull
    private LocalDateTime dateTime;
    @Column(name = "actionType", nullable = false)
    @NonNull
    private String actionType;
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @NonNull
    private Employee employee;
}
