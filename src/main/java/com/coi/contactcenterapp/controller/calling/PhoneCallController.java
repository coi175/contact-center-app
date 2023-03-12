package com.coi.contactcenterapp.controller.calling;

import com.coi.contactcenterapp.domain.dto.calling.CallsRequest;
import com.coi.contactcenterapp.domain.dto.calling.Contact_DTO;
import com.coi.contactcenterapp.domain.dto.calling.PhoneCall_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.mapper.calling.PhoneCallListMapper;
import com.coi.contactcenterapp.domain.mapper.calling.PhoneCallMapper;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.service.calling.PhoneCallService;
import com.coi.contactcenterapp.service.info.ActionLogService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class PhoneCallController {
    private final AuthUtils authUtils;
    private final PhoneCallService phoneCallService;
    private final PhoneCallMapper phoneCallMapper;
    private final PhoneCallListMapper phoneCallListMapper;
    private final ActionLogService actionLogService;

    /**
     * Get all calls but filtered with parameters: contactId, taskId, operatorId
     * If all parameters are null, then return all contacts from db.
     * If some parameters are null, then filter only with non-null parameters.
     * @param callsRequest
     * @return ResponseEntity<List<PhoneCall_DTO>>
     */
    @PostMapping("calls/")
    public ResponseEntity<List<PhoneCall_DTO>> getCallsByParams(@RequestBody CallsRequest callsRequest) {
        List<PhoneCall_DTO> phoneCallDtos = phoneCallListMapper.toDTO(phoneCallService
                .getPhoneCallsByParams(callsRequest.getContactId(), callsRequest.getTaskId(), callsRequest.getOperatorId()));
        return ResponseEntity.ok(phoneCallDtos);
    }

    @GetMapping("call/{callId}")
    public ResponseEntity<PhoneCall_DTO> getCall(@PathVariable Long callId) {
        PhoneCall phoneCall = phoneCallService.getEntityById(callId).orElseThrow(() -> new EntityNotFoundException("Звонок не найден"));
        return ResponseEntity.ok(phoneCallMapper.toDTO(phoneCall));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("call/add")
    public ResponseEntity<?> createCall(@RequestBody PhoneCall_DTO phoneCallDto) {
        PhoneCall phoneCall = phoneCallService.addPhoneCall(phoneCallMapper.toEntity(phoneCallDto));
        actionLogService.log(String.format("Добавлен звонок с id = %s", phoneCall.getPhoneCallId()), "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity.ok(phoneCall);
    }
}
