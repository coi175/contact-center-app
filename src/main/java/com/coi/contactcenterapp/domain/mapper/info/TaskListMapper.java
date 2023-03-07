package com.coi.contactcenterapp.domain.mapper.info;

import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper {
    List<Task_DTO> toDTO(List<Task> task);
    List<Task> toEntity(List<Task_DTO> taskDto);
}
