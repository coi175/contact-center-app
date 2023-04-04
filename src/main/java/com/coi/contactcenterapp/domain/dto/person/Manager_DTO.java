package com.coi.contactcenterapp.domain.dto.person;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager_DTO {
    private Integer managerId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer directorId;
}
