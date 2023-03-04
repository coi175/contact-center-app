package com.coi.contactcenterapp.domain.dto.calling;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact_DTO {
    private String contactId;
    private String phoneNumber;
    private String fullName;
    private String contactNote;
    private String contactType;
    private String contactStatus;
}
