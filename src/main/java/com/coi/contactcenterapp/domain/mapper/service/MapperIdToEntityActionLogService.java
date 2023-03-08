package com.coi.contactcenterapp.domain.mapper.service;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.repository.ContactRepository;
import com.coi.contactcenterapp.repository.EmployeeRepository;
import com.coi.contactcenterapp.repository.ManagerRepository;
import com.coi.contactcenterapp.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapperIdToEntityActionLogService {
    private final EmployeeRepository employeeRepository;

    public Employee mapEmployee(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
