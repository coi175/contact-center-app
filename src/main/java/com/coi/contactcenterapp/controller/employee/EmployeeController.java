package com.coi.contactcenterapp.controller.employee;

import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.mapper.person.DirectorMapper;
import com.coi.contactcenterapp.domain.mapper.person.ManagerMapper;
import com.coi.contactcenterapp.domain.mapper.person.OperatorMapper;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class EmployeeController {
    private final AuthUtils authUtils;
    private final OperatorMapper operatorMapper;
    private final ManagerMapper managerMapper;
    private final DirectorMapper directorMapper;

    @GetMapping("employee/entity")
    public ResponseEntity<?> getEmployeeId() {
        Employee employee = authUtils.getEmployeeFromAuth();
        if (employee.getManager() != null) {
            Manager manager = employee.getManager();
            manager.setEmployee(employee);
            return ResponseEntity.ok(managerMapper.toDTO(manager));
        } else if (employee.getOperator() != null) {
            Operator operator = employee.getOperator();
            operator.setEmployee(employee);
            return ResponseEntity.ok(operatorMapper.toDTO(operator));
        } else if (employee.getDirector() != null) {
            Director director = employee.getDirector();
            director.setEmployee(employee);
            return ResponseEntity.ok(directorMapper.toDTO(director));
        } else {
            return ResponseEntity.status(404).body("Сущность авторизации не найдена.");
        }
    }
}
