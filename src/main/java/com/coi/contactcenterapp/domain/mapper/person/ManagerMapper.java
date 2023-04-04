package com.coi.contactcenterapp.domain.mapper.person;

import com.coi.contactcenterapp.domain.dto.person.Manager_DTO;
import com.coi.contactcenterapp.domain.dto.person.Operator_DTO;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityEmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityEmployeeService.class })
public interface ManagerMapper {
    @Mapping(source = "employee.firstName", target = "firstName")
    @Mapping(source = "employee.lastName", target = "lastName")
    @Mapping(source = "employee.email", target = "email")
    @Mapping(source = "director.directorId", target = "directorId")
    Manager_DTO toDTO(Manager managerDto);

    @Mapping(source = "directorId", target = "director")
    Manager toEntity(Manager_DTO manager);

    List<Manager_DTO> toDTO(List<Manager> managerDto);

    List<Manager> toEntity(List<Manager_DTO> manager);
}