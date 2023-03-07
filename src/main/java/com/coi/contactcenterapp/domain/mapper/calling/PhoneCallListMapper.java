package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.PhoneCall_DTO;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneCallListMapper {
    List<PhoneCall_DTO> toDTO(List<PhoneCall> phoneCalls);
    List<PhoneCall> toEntity(List<PhoneCall_DTO> phoneCallDtos);
}
