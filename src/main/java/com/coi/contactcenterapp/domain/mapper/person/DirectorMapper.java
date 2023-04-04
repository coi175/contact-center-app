package com.coi.contactcenterapp.domain.mapper.person;

import com.coi.contactcenterapp.domain.dto.person.Director_DTO;
import com.coi.contactcenterapp.domain.dto.person.Operator_DTO;
import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityEmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityEmployeeService.class })
public interface DirectorMapper {
    @Mapping(source = "employee.firstName", target = "firstName")
    @Mapping(source = "employee.lastName", target = "lastName")
    @Mapping(source = "employee.email", target = "email")
    Director_DTO toDTO(Director directorDto);

    Director toEntity(Director_DTO director);

    List<Director_DTO> toDTO(List<Director> directorDto);

    List<Director> toEntity(List<Director_DTO> director);
}