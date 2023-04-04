package com.coi.contactcenterapp.domain.mapper.calling;

import com.coi.contactcenterapp.domain.dto.calling.Contact_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Contact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact_DTO toDTO(Contact contact);
    Contact toEntity(Contact_DTO contactDto);
    List<Contact_DTO> toDTO(List<Contact> contacts);
    List<Contact> toEntity(List<Contact_DTO> contactDtos);
}
