package com.coi.contactcenterapp.domain.dto.calling;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCall_DTO {
    private Long phoneCallId;
    private LocalDateTime callDate;
    private Integer callDuration;
    private String callStatus;
    private String contactId;
    private Long taskId;
    private Integer operatorId;
}
