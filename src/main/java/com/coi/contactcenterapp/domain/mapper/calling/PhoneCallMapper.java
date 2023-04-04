package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.PhoneCall_DTO;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.mapper.service.MapperIdToEntityPhoneCallService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MapperIdToEntityPhoneCallService.class })
public interface PhoneCallMapper {
    @Mapping(source = "contact.contactId", target = "contactId")
    @Mapping(source = "task.taskId", target = "taskId")
    @Mapping(source = "operator.operatorId", target = "operatorId")
    @Mapping(source = "contact.phoneNumber", target = "phoneNumber")
    PhoneCall_DTO toDTO(PhoneCall phoneCall);
    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "taskId", target = "task")
    @Mapping(source = "operatorId", target = "operator")
    PhoneCall toEntity(PhoneCall_DTO phoneCallDto);

    List<PhoneCall_DTO> toDTO(List<PhoneCall> phoneCalls);
    List<PhoneCall> toEntity(List<PhoneCall_DTO> phoneCallDtos);
}
