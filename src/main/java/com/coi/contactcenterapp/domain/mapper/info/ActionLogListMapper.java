package com.coi.contactcenterapp.domain.mapper.info;

import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityActionLogService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityActionLogService.class })
public interface ActionLogListMapper {
    @Mapping(source = "employee.employeeId", target = "employeeId")
    List<ActionLog_DTO> toDTO(List<ActionLog> actionLogs);
    @Mapping(source = "employeeId", target = "employee")
    List<ActionLog> toEntity(List<ActionLog_DTO> actionLogDtos);
}
