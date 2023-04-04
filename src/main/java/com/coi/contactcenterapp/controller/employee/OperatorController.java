package com.coi.contactcenterapp.controller.employee;

import com.coi.contactcenterapp.domain.dto.person.Operator_DTO;
import com.coi.contactcenterapp.domain.mapper.person.OperatorMapper;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.service.person.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class OperatorController {
    private final OperatorService operatorService;
    private final OperatorMapper operatorMapper;
    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("manager/operator/{operatorId}")
    public ResponseEntity<Operator_DTO> getOperators(@PathVariable Integer operatorId) {
        return ResponseEntity.ok(operatorMapper.toDTO(operatorService.getEntityById(operatorId).orElseThrow(() -> new EntityNotFoundException("Оператор не найден"))));
    }
}
