package com.coi.contactcenterapp.domain.mapper.service;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapperIdToEntityEmployeeService {
    private final DirectorRepository directorRepository;
    private final ManagerRepository managerRepository;
    private final OperatorRepository operatorRepository;
    private final EmployeeRepository employeeRepository;

    public Employee mapEmployee(Integer id) {
        if (id == null) {
            return null;
        }
        return employeeRepository.findById(id).orElse(null);
    }
    public Manager mapManager(Integer id) {
        if (id == null) {
            return null;
        }
        return managerRepository.findById(id).orElse(null);
    }
    public Operator mapOperator(Integer id) {
        if (id == null) {
            return null;
        }
        return operatorRepository.findById(id).orElse(null);
    }
    public Director manDirector(Integer id) {
        if (id == null) {
            return null;
        }
        return directorRepository.findById(id).orElse(null);
    }
}

