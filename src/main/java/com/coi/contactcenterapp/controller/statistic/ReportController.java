package com.coi.contactcenterapp.controller.statistic;

import com.coi.contactcenterapp.domain.dto.report.OperatorReport;
import com.coi.contactcenterapp.domain.dto.report.ReportRequest;
import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.mapper.calling.TaskMapper;
import com.coi.contactcenterapp.service.calling.ContactService;
import com.coi.contactcenterapp.service.calling.PhoneCallService;
import com.coi.contactcenterapp.service.calling.TaskService;
import com.coi.contactcenterapp.service.info.ActionLogService;
import com.coi.contactcenterapp.service.person.DirectorService;
import com.coi.contactcenterapp.service.person.ManagerService;
import com.coi.contactcenterapp.service.person.OperatorService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class ReportController {
    private final AuthUtils authUtils;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final ContactService contactService;
    private final ActionLogService actionLogService;
    private final OperatorService operatorService;
    private final DirectorService directorService;
    private final ManagerService managerService;
    private final PhoneCallService phoneCallService;
    @PostMapping("report/operator/get")
    public ResponseEntity<List<OperatorReport>>getOperatorReport(@RequestBody ReportRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm:ss");
        LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
        List<OperatorReport> reports = new ArrayList<>();
        while (startDate.compareTo(endDate) < 1) {
            Integer averageDuration = phoneCallService.getAverageCallDuration(request.getEmployeeId(), startDate);
            if (averageDuration == null) {
                averageDuration = 0;
            }
            Integer successCalls = phoneCallService.getSuccessCalls(request.getEmployeeId(), startDate).size();
            Integer otherCalls = phoneCallService.getOtherCalls(request.getEmployeeId(), startDate).size();
            Integer successTasks = taskService.getSuccessTasks(request.getEmployeeId(), startDate).size();
            Integer otherTasks = taskService.getOtherTasks(request.getEmployeeId(), startDate).size();

            OperatorReport operatorReport = OperatorReport.builder()
                    .averageDuration(averageDuration)
                    .successCalls(successCalls)
                    .successTasks(successTasks)
                    .otherCalls(otherCalls)
                    .otherTasks(otherTasks)
                    .reportDate(startDate)
                    .operatorCount(1)
                    .managerCount(0)
                    .build();

            reports.add(operatorReport);
            startDate = startDate.plusDays(1);
        }

        reports.sort(Comparator.comparing(OperatorReport::getReportDate));
        return ResponseEntity.ok(reports);
    }

    @PostMapping("report/manager/get")
    public ResponseEntity<?> getManagerReport(@RequestBody ReportRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm:ss");
        LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
        List<OperatorReport> reports = new ArrayList<>();
        Manager manager = managerService.getEntityById(request.getEmployeeId()).orElseGet(null);
        if (manager == null) {
            return ResponseEntity.status(403).body("Менеджер не найден");
        }
        List<Operator> operators = manager.getOperators();
        while (startDate.compareTo(endDate) < 1) {
            Integer averageDuration = 0;
            Integer successCalls = 0;
            Integer otherCalls = 0;
            Integer successTasks = 0;
            Integer otherTasks = 0;
            int i = 0;
            for(Operator operator : operators) {
                 averageDuration += phoneCallService.getAverageCallDuration(operator.getOperatorId(), startDate);
                 i++;
                 successCalls += phoneCallService.getSuccessCalls(operator.getOperatorId(), startDate).size();
                 otherCalls += phoneCallService.getOtherCalls(operator.getOperatorId(), startDate).size();
                 successTasks += taskService.getSuccessTasks(operator.getOperatorId(), startDate).size();
                 otherTasks += taskService.getOtherTasks(operator.getOperatorId(), startDate).size();
            }
            averageDuration /= i;
            OperatorReport operatorReport = OperatorReport.builder()
                    .averageDuration(averageDuration)
                    .successCalls(successCalls)
                    .successTasks(successTasks)
                    .otherCalls(otherCalls)
                    .otherTasks(otherTasks)
                    .operatorCount(operators.size())
                    .reportDate(startDate)
                    .managerCount(1)
                    .build();


            reports.add(operatorReport);
            startDate = startDate.plusDays(1);
        }

        reports.sort(Comparator.comparing(OperatorReport::getReportDate));
        return ResponseEntity.ok(reports);
    }

    @PostMapping("report/director/get")
    public ResponseEntity<?> getDirectorReport(@RequestBody ReportRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm:ss");
        LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
        List<OperatorReport> reports = new ArrayList<>();
        Director director = directorService.getEntityById(request.getEmployeeId()).orElseGet(null);
        if (director == null) {
            return ResponseEntity.status(403).body("Менеджер не найден");
        }
        List<Manager> managers = director.getManagers();
        Integer operatorSize = 0;
        while (startDate.compareTo(endDate) < 1) {
            Integer averageDuration = 0;
            Integer successCalls = 0;
            Integer otherCalls = 0;
            Integer successTasks = 0;
            Integer otherTasks = 0;
            int i = 0;
            for(Manager manager : managers) {
                List<Operator> operators = manager.getOperators();
                for(Operator operator : operators) {
                    averageDuration += phoneCallService.getAverageCallDuration(operator.getOperatorId(), startDate);
                    i++;
                    successCalls += phoneCallService.getSuccessCalls(operator.getOperatorId(), startDate).size();
                    otherCalls += phoneCallService.getOtherCalls(operator.getOperatorId(), startDate).size();
                    successTasks += taskService.getSuccessTasks(operator.getOperatorId(), startDate).size();
                    otherTasks += taskService.getOtherTasks(operator.getOperatorId(), startDate).size();

                    operatorSize++;
                }
            }

            averageDuration /= i;
            OperatorReport operatorReport = OperatorReport.builder()
                    .averageDuration(averageDuration)
                    .successCalls(successCalls)
                    .successTasks(successTasks)
                    .otherCalls(otherCalls)
                    .otherTasks(otherTasks)
                    .operatorCount(operatorSize)
                    .reportDate(startDate)
                    .managerCount(managers.size())
                    .build();

            reports.add(operatorReport);
            startDate = startDate.plusDays(1);
        }

        reports.sort(Comparator.comparing(OperatorReport::getReportDate));
        return ResponseEntity.ok(reports);
    }
}
