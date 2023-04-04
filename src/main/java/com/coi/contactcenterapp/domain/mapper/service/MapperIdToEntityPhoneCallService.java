package com.coi.contactcenterapp.domain.mapper.service;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for mapper which map ids from PhoneCall_DTO to Entities from PhoneCall
 */
@RequiredArgsConstructor
@Service
public class MapperIdToEntityPhoneCallService {
    private final ContactRepository contactRepository;
    private final TaskRepository taskRepository;
    private final OperatorRepository operatorRepository;

    public Contact mapContact(String id) {
        return contactRepository.findById(id).orElse(null);
    }
    public Task mapTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    public Operator mapOperator(Integer id) {
        return operatorRepository.findById(id).orElse(null);
    }
}
