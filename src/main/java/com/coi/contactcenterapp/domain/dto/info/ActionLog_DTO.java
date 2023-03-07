package com.coi.contactcenterapp.domain.dto.info;

import com.coi.contactcenterapp.domain.entity.person.Employee;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLog_DTO {
    private Long actionLogId;
    private String logMessage;
    private LocalDateTime dateTime;
    private String actionType;
    private Employee employee;
}
