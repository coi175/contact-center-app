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
public class PhoneCall_DTO {
    private Long phoneCallId;
    @JsonFormat(pattern="dd.MM.YY - hh:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime callDate;
    private Integer callDuration;
    private String callStatus;
    private String contactId;
    private String phoneNumber;
    private Long taskId;
    private Integer operatorId;
}
