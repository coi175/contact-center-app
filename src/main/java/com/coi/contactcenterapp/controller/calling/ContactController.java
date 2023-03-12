package com.coi.contactcenterapp.controller.calling;

import com.coi.contactcenterapp.domain.dto.calling.Contact_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.mapper.calling.ContactListMapper;
import com.coi.contactcenterapp.domain.mapper.calling.ContactMapper;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.service.calling.ContactService;
import com.coi.contactcenterapp.service.info.ActionLogService;
import com.coi.contactcenterapp.util.AuthUtils;
import com.coi.contactcenterapp.util.FakeCRMUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class ContactController {
    private final AuthUtils authUtils;
    private final ContactMapper contactMapper;
    private final ContactListMapper contactListMapper;
    private final ContactService contactService;
    private final ActionLogService actionLogService;

    @GetMapping("contact/{contactId}")
    public ResponseEntity<Contact_DTO> getContact(@PathVariable String contactId) {
        Contact contact = contactService.getEntityById(contactId).orElseThrow(() -> new EntityNotFoundException("Контакт не найден"));
        return ResponseEntity.ok(contactMapper.toDTO(contact));
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("manager/contacts")
    public ResponseEntity<List<Contact_DTO>> getContactsToManager() {
        return ResponseEntity.ok(contactListMapper.toDTO(contactService.getAllContacts()));
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("contacts/manager/crm/receive")
    public ResponseEntity<?> receiveContactsFromCRM() {
        actionLogService.log("Попытка получить контакты из CRM", "INFO", authUtils.getEmployeeFromAuth());
        Map<String, Contact> contactsFromDatabase = contactService.getAllContacts().stream().collect(Collectors.toMap(Contact::getContactId, contact -> contact));
        List<Contact_DTO> contactsFromCrm = new ArrayList<>(FakeCRMUtil.contacts.values());
        List<Contact> contactListToAdd = new ArrayList<>();
        for (Contact_DTO contactDto : contactsFromCrm) {
            if (contactsFromDatabase.containsKey(contactDto.getContactId())) {
                // some logic for renew contact
            } else {
                contactListToAdd.add(contactMapper.toEntity(contactDto));
            }
        }
        contactService.addAllContacts(contactListToAdd);
        actionLogService.log("Данные из CRM получены", "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity.ok(contactListToAdd);
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("/contacts/manager/crm/push")
    public ResponseEntity<?> pushContactsToCRM() {
        actionLogService.log("Попытка отправить контакты в CRM", "INFO", authUtils.getEmployeeFromAuth());
        List<Contact> contacts = contactService.getAllContacts();
        List<Contact_DTO> contactDTOs = contactListMapper.toDTO(contacts);
        // push to CRM
        actionLogService.log("Данные отправлены в CRM", "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity.ok(contactDTOs);
    }
    @PutMapping("/contact/update/{contactId}")
    public ResponseEntity<?> updateContact(@PathVariable String contactId, @RequestBody Contact_DTO requestContact) {
        Contact contact = contactService.getEntityById(contactId).orElseThrow(() -> new EntityNotFoundException("Контакт не найден"));
        contact.setContactStatus(requestContact.getContactStatus());
        contact.setContactNote(requestContact.getContactNote());
        contactService.addContact(contact);
        actionLogService.log(String.format("Обновил контакт с id = %s", contact.getContactId()), "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity.ok(contactMapper.toDTO(contact));
    }
}
