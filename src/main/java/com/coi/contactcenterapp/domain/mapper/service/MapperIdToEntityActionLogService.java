package com.coi.contactcenterapp.domain.mapper.service;

import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for mapper which map ids from ActionLog_DTO to Entities from ActionLog
 */
@RequiredArgsConstructor
@Service
public class MapperIdToEntityActionLogService {
    private final EmployeeRepository employeeRepository;

    public Employee mapEmployee(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
