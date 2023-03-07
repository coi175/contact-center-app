package com.coi.contactcenterapp.domain.dto.calling;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAutoCreationRequest {
    private String contactId;
    private String taskDescription;
}
