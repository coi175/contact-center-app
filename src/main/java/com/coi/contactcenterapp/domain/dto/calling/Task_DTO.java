package com.coi.contactcenterapp.domain.dto.calling;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task_DTO {
    private Long taskId;
    private String taskDescription;
    private String taskStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Contact contact;
    private Manager manager;
    private Operator operator;
}
