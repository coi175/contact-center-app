package com.coi.contactcenterapp.domain.mapper.service;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.repository.ContactRepository;
import com.coi.contactcenterapp.repository.ManagerRepository;
import com.coi.contactcenterapp.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for mapper which map ids from Task_DTO to Entities from Task
 */
@RequiredArgsConstructor
@Service
public class MapperIdToEntityTaskService {
    private final ContactRepository contactRepository;
    private final ManagerRepository managerRepository;
    private final OperatorRepository operatorRepository;

    public Contact mapContact(String id) {
        return contactRepository.findById(id).orElse(null);
    }
    public Manager mapManager(Integer id) {
        return managerRepository.findById(id).orElse(null);
    }
    public Operator mapOperator(Integer id) {
        return operatorRepository.findById(id).orElse(null);
    }
}
