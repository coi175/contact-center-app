package com.coi.contactcenterapp.domain.dto.calling;


import lombok.*;
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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String contactId;
    private Integer managerId;
    private Integer operatorId;
}
