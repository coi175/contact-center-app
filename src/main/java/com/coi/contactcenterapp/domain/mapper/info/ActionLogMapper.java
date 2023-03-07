package com.coi.contactcenterapp.domain.mapper.info;

import com.coi.contactcenterapp.domain.dto.calling.PhoneCall_DTO;
import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionLogMapper {
    ActionLog_DTO toDTO(ActionLog actionLog);
    ActionLog toEntity(ActionLog_DTO actionLogDto);
}
