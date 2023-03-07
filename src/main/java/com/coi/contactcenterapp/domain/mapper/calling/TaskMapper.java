package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task_DTO toDTO(Task task);
    Task toEntity(Task_DTO taskDto);
}