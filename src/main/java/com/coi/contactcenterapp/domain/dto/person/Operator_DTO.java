package com.coi.contactcenterapp.domain.dto.person;

import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operator_DTO {
    private Integer operatorId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer managerId;
}
