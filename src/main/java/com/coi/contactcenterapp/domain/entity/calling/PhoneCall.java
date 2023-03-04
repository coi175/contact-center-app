package com.coi.contactcenterapp.domain.entity.calling;

import com.coi.contactcenterapp.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="phone_call")
public class PhoneCall implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="phone_call_id")
    private Long phoneCallId;
    @Column(name="call_date", nullable = false)
    @NonNull
    private LocalDateTime callDate;
    @Column(name="call_duration", nullable = false)
    @NonNull
    private Integer callDuration;
    @Column(name="call_status", nullable = false)
    @NonNull
    private String callStatus;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "contact_id")
    @NonNull
    private Contact contact;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "task_id")
    @NonNull
    private Task task;
}
