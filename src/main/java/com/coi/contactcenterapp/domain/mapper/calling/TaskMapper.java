package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityTaskService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityTaskService.class, TaskMapper.class })
public interface TaskMapper {
    @Mapping(source = "task.contact.contactId", target = "contactId")
    @Mapping(source = "task.manager.managerId", target = "managerId")
    @Mapping(source = "task.operator.operatorId", target = "operatorId")
    @Mapping(source = "task.operator.employee.firstName", target = "operatorFirstName")
    @Mapping(source = "task.operator.employee.lastName", target = "operatorLastName")
    @Mapping(source = "task.contact.phoneNumber", target = "phoneNumber")
    Task_DTO toDTO(Task task);
    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "managerId", target = "manager")
    @Mapping(source = "operatorId", target = "operator")
    Task toEntity(Task_DTO taskDto);
    // TODO: Make functional for mapping entity from id in DTO -> Entity


    List<Task_DTO> toDTO(List<Task> task);

    List<Task> toEntity(List<Task_DTO> taskDto);
}