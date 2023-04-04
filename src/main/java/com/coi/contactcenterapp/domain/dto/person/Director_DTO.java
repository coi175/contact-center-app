package com.coi.contactcenterapp.domain.dto.person;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Director_DTO {
    private Integer directorId;
    private String firstName;
    private String lastName;
    private String email;
}
