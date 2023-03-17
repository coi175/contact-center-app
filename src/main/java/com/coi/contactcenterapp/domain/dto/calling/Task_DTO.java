package com.coi.contactcenterapp.domain.dto.calling;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task_DTO {
    private Long taskId;
    private String taskDescription;
    private String taskStatus;
    @JsonFormat(pattern="dd.MM.YY - hh:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String contactId;
    private String phoneNumber;
    private Integer managerId;
    private Integer operatorId;
    private String operatorFirstName;
    private String operatorLastName;
}
