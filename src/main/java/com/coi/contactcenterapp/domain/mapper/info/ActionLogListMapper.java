package com.coi.contactcenterapp.domain.mapper.info;

import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActionLogListMapper {
    List<ActionLog_DTO> toDTO(List<ActionLog> actionLogs);
    List<ActionLog> toEntity(List<ActionLog_DTO> actionLogDtos);
}
