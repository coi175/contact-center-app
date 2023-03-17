package com.coi.contactcenterapp.domain.dto.info;

import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLog_DTO {
    private Long actionLogId;
    private String logMessage;
    @JsonFormat(pattern="dd.MM.YY - hh:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime dateTime;
    private String actionType;
    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
}
