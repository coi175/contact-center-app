package com.coi.contactcenterapp.controller.employee;

import com.coi.contactcenterapp.domain.dto.person.Operator_DTO;
import com.coi.contactcenterapp.domain.mapper.person.OperatorMapper;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class ManagerController {
    private final AuthUtils authUtils;
    private final OperatorController operatorController;
    private final OperatorMapper operatorMapper;

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("manager/operators")
    public ResponseEntity<List<Operator_DTO>> getOperators() {
        return ResponseEntity.ok(operatorMapper.toDTO(authUtils.getManagerFromAuth().getOperators()));
    }
}
