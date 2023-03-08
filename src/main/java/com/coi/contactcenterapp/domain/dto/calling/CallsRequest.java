package com.coi.contactcenterapp.domain.dto.calling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallsRequest {
    private String contactId = null;
    private Long taskId = null;
    private Integer operatorId = null;
}
