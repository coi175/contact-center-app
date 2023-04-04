package com.coi.contactcenterapp.controller.employee;

import com.coi.contactcenterapp.domain.dto.person.Manager_DTO;
import com.coi.contactcenterapp.domain.dto.person.Operator_DTO;
import com.coi.contactcenterapp.domain.mapper.person.ManagerMapper;
import com.coi.contactcenterapp.service.person.DirectorService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class DirectorController {
    private final DirectorService directorService;
    private final ManagerMapper managerMapper;
    private final AuthUtils authUtils;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("director/managers")
    public ResponseEntity<List<Manager_DTO>> getManagers() {
        return ResponseEntity.ok(managerMapper.toDTO(authUtils.getDirectorFromAuth().getManagers()));
    }

}
