package com.coi.contactcenterapp.controller.employee;

import com.coi.contactcenterapp.domain.dto.info.ActionLog_DTO;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import com.coi.contactcenterapp.domain.mapper.info.ActionLogMapper;
import com.coi.contactcenterapp.service.info.ActionLogService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class LogController {
    private final AuthUtils authUtils;
    private final ActionLogService logService;
    private final ActionLogMapper actionLogMapper;

    @PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
    @GetMapping("logs")
    public ResponseEntity<List<ActionLog_DTO>> getOperators() {
        List<ActionLog> actionLogs = logService.getAllLogs();
        actionLogs.sort(Comparator.comparing(ActionLog::getDateTime).reversed());
        return ResponseEntity.ok(actionLogMapper.toDTO(actionLogs));
    }

    @GetMapping("time")
    public ResponseEntity<String> getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return ResponseEntity.ok(LocalTime.now().format(formatter));
    }
}
