package com.coi.contactcenterapp.controller.calling;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping("contact/{contactId}")
    public Contact_DTO getContact(@PathVariable Long contactId) {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("manager/contacts")
    public ResponseEntity<List<Contact_DTO>> getContactsToManager() {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/contacts/manager/crm/receive")
    public ResponseEntity<?> receiveContactsFromCRM() {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("/contacts/manager/crm/push")
    public ResponseEntity<?> pushContactsToCRM() {

    }

    @PutMapping("/contact/update/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody Task_DTO requestTask) {

    }
}
