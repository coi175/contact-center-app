package com.coi.contactcenterapp.domain.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportRequest {
    private Integer employeeId;
    private String startDate;
    private String endDate;
}
