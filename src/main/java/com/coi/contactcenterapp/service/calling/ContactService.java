package com.coi.contactcenterapp.service.calling;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.repository.ContactRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContactService implements BaseService<Contact, String> {
    private final ContactRepository contactRepository;
    @Override
    public Optional<Contact> getEntityById(String s) {
        return contactRepository.findById(s);
    }
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public void addAllContacts(List<Contact> contactList) {
        contactRepository.saveAll(contactList);
    }
}
