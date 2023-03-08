package com.coi.contactcenterapp.controller.calling;

import com.coi.contactcenterapp.domain.dto.auth.JwtAuthentication;
import com.coi.contactcenterapp.domain.dto.calling.TaskAutoCreationRequest;
import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.mapper.calling.TaskMapper;
import com.coi.contactcenterapp.domain.mapper.info.TaskListMapper;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.service.calling.ContactService;
import com.coi.contactcenterapp.service.calling.TaskService;
import com.coi.contactcenterapp.service.info.ActionLogService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final AuthUtils authUtils;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final ContactService contactService;
    private final TaskListMapper taskListMapper;
    private final ActionLogService actionLogService;

    @GetMapping("task/{taskId}")
    public ResponseEntity<Task_DTO> getTask(@PathVariable Long taskId) {
        Task task = taskService.getEntityById(taskId).orElseThrow(() -> new EntityNotFoundException("Задача не найдена"));
        return ResponseEntity.ok(taskMapper.toDTO(task));
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("manager/{managerId}/tasks")
    public ResponseEntity<List<Task_DTO>> getTasksByManager(@PathVariable Integer managerId) {
        if (managerId == null) {
            managerId = authUtils.getManagerFromAuth().getManagerId();
        }
        return ResponseEntity
                .ok(taskListMapper
                        .toDTO(taskService.getAllTasksByParams(null, managerId, null, null)));
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("tasks")
    public ResponseEntity<List<Task_DTO>> getTasksByParams(@RequestBody Task_DTO taskParams) {
        return ResponseEntity
                .ok(taskListMapper
                        .toDTO(taskService.getAllTasksByParams(taskParams.getOperatorId(),
                                taskParams.getManagerId(),
                                taskParams.getContactId(),
                                taskParams.getTaskStatus())));
    }

    @PostMapping("operator/{operatorId}/tasks")
    public ResponseEntity<List<Task_DTO>> getTasksByOperator(@PathVariable Integer operatorId) {
        if (operatorId == null) {
            operatorId = authUtils.getOperatorFromAuth().getOperatorId();
        }
        return ResponseEntity
                .ok(taskListMapper
                        .toDTO(taskService.getAllTasksByParams(operatorId, null, null, null)));
    }

    /**
     * Method creates tasks from contacts depends on its status.
     * Parameters are describe task's count and types.
     * @return
     */
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("tasks/manager/create/auto")
    public ResponseEntity<Integer> autoCreateTasks(@RequestBody List<TaskAutoCreationRequest> taskAutoCreationRequestList) {
        actionLogService.log(String.format("Попытка автоматически создать %s задач", taskAutoCreationRequestList.size()), "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity.ok(taskService.addAllTasks(
                taskAutoCreationRequestList.stream()
                        .map(t -> Task_DTO.builder().taskDescription(t.getTaskDescription())
                                .startDate(LocalDateTime.now())
                                .taskStatus("NEW")
                                .managerId(authUtils.getManagerFromAuth().getManagerId())
                                .contactId(contactService.getEntityById(t.getContactId()).orElse(new Contact()).getContactId())
                                .build())
                        .filter(t -> t.getContactId() != null)
                        .map(taskMapper::toEntity)
                        .toList())
                .size()
        );
    }

    //@PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/tasks/manager/create")
    public ResponseEntity<Integer> createTasks(@RequestBody List<Task_DTO> requestTasks) {
        actionLogService.log(String.format("Попытка создать задач: %s ", requestTasks.size()), "INFO", authUtils.getEmployeeFromAuth());
        Contact contact = new Contact("test", "test", "test", "test", "test");
        Task task = taskMapper.toEntity(requestTasks.get(0));
        return ResponseEntity.ok(taskService.addAllTasks(taskListMapper.toEntity(requestTasks)).size());
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("/task/manager/update/{taskId}")
    public ResponseEntity<Task> managerUpdateTask(@PathVariable Long taskId, @RequestBody Task_DTO requestTask) {
        actionLogService.log(String.format("Попытка менеджера обновить задачу с id: %s ", taskId), "INFO", authUtils.getEmployeeFromAuth());
        Task task = taskService.getEntityById(taskId).orElseThrow(() -> new EntityNotFoundException("Задача не найдена"));
        Task_DTO taskDto = taskMapper.toDTO(task);
        if (requestTask.getTaskDescription() != null) {
            taskDto.setTaskDescription(requestTask.getTaskDescription());
        }
        if (requestTask.getTaskStatus() != null) {
            taskDto.setTaskStatus(requestTask.getTaskStatus());
        }
        if (requestTask.getManagerId() != null) {
            taskDto.setManagerId(requestTask.getManagerId());
        }
        if (requestTask.getOperatorId() != null) {
            taskDto.setOperatorId(requestTask.getOperatorId());
        }
        if (requestTask.getContactId() != null) {
            taskDto.setContactId(requestTask.getContactId());
        }
        return ResponseEntity.ok(taskService.addTask(taskMapper.toEntity(taskDto)));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/task/operator/update/{taskId}")
    public ResponseEntity<?> operatorUpdateTask(@PathVariable Long taskId, @RequestBody Task_DTO requestTask) {
        actionLogService.log(String.format("Попытка оператора обновить задачу с id: %s ", taskId), "INFO", authUtils.getEmployeeFromAuth());
        Task task = taskService.getEntityById(taskId).orElseThrow(() -> new EntityNotFoundException("Задача не найдена"));
        Task_DTO taskDto = taskMapper.toDTO(task);
        if (requestTask.getTaskStatus() != null) {
            taskDto.setTaskStatus(requestTask.getTaskStatus());
        }
        if (requestTask.getOperatorId() != null) {
            taskDto.setOperatorId(requestTask.getOperatorId());
        }
        return ResponseEntity.ok(taskService.addTask(taskMapper.toEntity(taskDto)));
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/tasks/delete")
    public ResponseEntity<?> deleteTasks(@RequestBody List<Long> requestTasksId) {
        actionLogService.log(String.format("Попытка удалить задач: %s ", requestTasksId.size()), "INFO", authUtils.getEmployeeFromAuth());
        for(Long taskId : requestTasksId) {
            taskService.deleteTask(taskId);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
