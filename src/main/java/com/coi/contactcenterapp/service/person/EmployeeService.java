package com.coi.contactcenterapp.service.person;

import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.repository.EmployeeRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements BaseService<Employee, Integer> {
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getEntityById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }
}
