package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
