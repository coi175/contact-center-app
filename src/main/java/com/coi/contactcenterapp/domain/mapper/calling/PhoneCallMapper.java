package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.Contact_DTO;
import com.coi.contactcenterapp.domain.dto.calling.PhoneCall_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneCallMapper {
    PhoneCall_DTO toDTO(PhoneCall phoneCall);
    PhoneCall toEntity(PhoneCall_DTO phoneCallDto);
}
