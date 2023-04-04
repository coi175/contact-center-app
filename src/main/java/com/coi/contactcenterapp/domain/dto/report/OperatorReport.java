package com.coi.contactcenterapp.domain.dto.report;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperatorReport {
    private LocalDate reportDate;
    private Integer averageDuration;
    private Integer successCalls;
    private Integer otherCalls;
    private Integer successTasks;
    private Integer otherTasks;
    private Integer operatorCount;
    private Integer managerCount;
}
