package com.coi.contactcenterapp.domain.mapper.info;

import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityTaskService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityTaskService.class })
public interface TaskListMapper {
    @Mapping(source = "contact.contactId", target = "contactId")
    @Mapping(source = "manager.managerId", target = "managerId")
    @Mapping(source = "operator.operatorId", target = "operatorId")
    List<Task_DTO> toDTO(List<Task> task);
    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "managerId", target = "manager")
    @Mapping(source = "operatorId", target = "operator")
    List<Task> toEntity(List<Task_DTO> taskDto);
}
