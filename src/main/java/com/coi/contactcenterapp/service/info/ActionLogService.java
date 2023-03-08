package com.coi.contactcenterapp.service.info;

import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import com.coi.contactcenterapp.domain.entity.person.Employee;
import com.coi.contactcenterapp.repository.ActionLogRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ActionLogService implements BaseService<ActionLog, Long> {
    private final ActionLogRepository actionLogRepository;

    @Override
    public Optional<ActionLog> getEntityById(Long actionLogId) {
        return actionLogRepository.findById(actionLogId);
    }

    public void add(ActionLog actionLog) {
        actionLogRepository.save(actionLog);
    }

    public void log(String message, String type, Employee employee) {
        ActionLog actionLog = new ActionLog(message, LocalDateTime.now(), type, employee);
        actionLogRepository.save(actionLog);
    }

    public List<ActionLog> getActionLogByEmployeeId(Integer employeeId) {
        return actionLogRepository.findAllByEmployeeEmployeeId(employeeId);
    }
}
