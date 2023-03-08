package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityTaskService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityTaskService.class })
public interface TaskMapper {
    @Mapping(source = "contact.contactId", target = "contactId")
    @Mapping(source = "manager.managerId", target = "managerId")
    @Mapping(source = "operator.operatorId", target = "operatorId")
    Task_DTO toDTO(Task task);
    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "managerId", target = "manager")
    @Mapping(source = "operatorId", target = "operator")
    Task toEntity(Task_DTO taskDto);
    // TODO: Make functional for mapping entity from id in DTO -> Entity
}