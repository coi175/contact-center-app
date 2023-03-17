package com.coi.contactcenterapp.domain.mapper.person;

import com.coi.contactcenterapp.domain.dto.person.Operator_DTO;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityTaskService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityTaskService.class })
public interface OperatorMapper {
    @Mapping(source = "employee.firstName", target = "firstName")
    @Mapping(source = "employee.lastName", target = "lastName")
    @Mapping(source = "employee.email", target = "email")
    @Mapping(source = "manager.managerId", target = "managerId")
    Operator_DTO toDTO(Operator operatorDto);

    @Mapping(source = "managerId", target = "manager")
    Operator toEntity(Operator_DTO operator);

    List<Operator_DTO> toDTO(List<Operator> operatorDto);

    List<Operator> toEntity(List<Operator_DTO> operator);
}
