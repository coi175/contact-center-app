package com.coi.contactcenterapp.domain.mapper.info;

import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityActionLogService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityActionLogService.class })
public interface ActionLogMapper {
    @Mapping(source = "employee.employeeId", target = "employeeId")
    ActionLog_DTO toDTO(ActionLog actionLog);
    @Mapping(source = "employeeId", target = "employee")
    ActionLog toEntity(ActionLog_DTO actionLogDto);
}
