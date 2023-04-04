package com.coi.contactcenterapp.controller.calling;

import com.coi.contactcenterapp.domain.dto.calling.TaskAutoCreationRequest;
import com.coi.contactcenterapp.domain.dto.calling.Task_DTO;
import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.mapper.calling.TaskMapper;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.service.calling.ContactService;
import com.coi.contactcenterapp.service.calling.TaskService;
import com.coi.contactcenterapp.service.info.ActionLogService;
import com.coi.contactcenterapp.service.person.OperatorService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class TaskController {
    private final AuthUtils authUtils;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final ContactService contactService;
    private final ActionLogService actionLogService;
    private final OperatorService operatorService;

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
                .ok(taskMapper
                        .toDTO(taskService.getAllTasksByParams(null, managerId, null, null)));
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("tasks")
    public ResponseEntity<List<Task_DTO>> getTasksByParams(@RequestBody Task_DTO taskParams) {
        List<Task_DTO> taskDtos = taskMapper
                        .toDTO(taskService.getAllTasksByParams(taskParams.getOperatorId(),
                                taskParams.getManagerId(),
                                taskParams.getContactId(),
                                taskParams.getTaskStatus()));
        taskDtos.sort(Comparator.comparing(Task_DTO::getStartDate).reversed());
        return ResponseEntity
                .ok(taskDtos);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("operator/tasks")
    public ResponseEntity<List<Task_DTO>> getTasksToOperator(@RequestBody Task_DTO taskParams) {
        List<Task_DTO> taskDtos = taskMapper
                .toDTO(taskService.getAllTasksByParams(null,
                        taskParams.getManagerId(),
                        taskParams.getContactId(),
                        taskParams.getTaskStatus()).stream()
                        .filter(t -> t.getTaskStatus().equals("NEW") || t.getTaskStatus().equals("RENEW"))
                        .filter(t -> t.getOperator() == null || t.getOperator().getOperatorId().equals(taskParams.getOperatorId()))
                        .toList()
                );
        taskDtos.sort(Comparator.comparing(Task_DTO::getStartDate).reversed().thenComparing(Task_DTO::getTaskStatus).reversed());
        return ResponseEntity
                .ok(taskDtos);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("operator/currentTask")
    public ResponseEntity<?> getCurrentTaskToOperator(@RequestBody Task_DTO taskParams) {
        Operator operator;
        if (taskParams.getOperatorId() == null) {
            operator = authUtils.getOperatorFromAuth();
        } else {
            operator = operatorService.getEntityById(taskParams.getOperatorId()).orElse(null);
        }

        if (operator == null) {
            return ResponseEntity.status(404).body("Оператор не найден");
        }
        Optional<Task> task = operator.getTaskList().stream().filter(t -> t.getTaskStatus().equals("ACTIVE")).findFirst();
        if (task.isPresent()) {
            return ResponseEntity.ok(taskMapper.toDTO(task.get()));
        }
        return ResponseEntity.status(404).body("Нет текущих задач");
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("operator/takeTask")
    public ResponseEntity<?> takeTaskToOperator(@RequestBody Task_DTO taskParams) {
        actionLogService.log(String.format("Попытка взять задачу %s оператором %s", taskParams.getTaskId(), taskParams.getOperatorId()), "INFO", authUtils.getEmployeeFromAuth());
        Operator operator = operatorService.getEntityById(taskParams.getOperatorId()).orElse(null);
        if (operator == null) {
            return ResponseEntity.status(404).body("Оператор не найден");
        }
        if (operator.getTaskList().stream()
                .filter(t -> t.getTaskStatus().equals("ACTIVE")).toList().size() > 0) {
            return ResponseEntity.status(403).body("У оператора уже есть активная задача");
        }

        Task task = taskService.getEntityById(taskParams.getTaskId()).orElse(null);
        if (task == null) {
            return ResponseEntity.status(403).body("Задача не найдена");
        }
        if (!task.getTaskStatus().equals("NEW")) {
            return ResponseEntity.status(403).body("Вы не можете взять задачу со статусом " + task.getTaskStatus());
        }

        if (task.getOperator() != null) {
            return ResponseEntity.status(403).body("Задача уже взята другим оператором");
        }
        task.setTaskStatus("ACTIVE");
        task.setOperator(operator);
        operator.getTaskList().add(task);
        operatorService.save(operator);
        actionLogService.log(String.format("Успешно взята задача %s оператором %s", taskParams.getTaskId(), taskParams.getOperatorId()), "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity
                .ok("200");
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("operator/task/close")
    public ResponseEntity<?> closeTask(@RequestBody Task_DTO taskParams) {
        actionLogService.log(String.format("Попытка завершить задачу %s оператором %s", taskParams.getTaskId(), taskParams.getOperatorId()), "INFO", authUtils.getEmployeeFromAuth());
        Operator operator = operatorService.getEntityById(taskParams.getOperatorId()).orElse(null);
        if (operator == null) {
            return ResponseEntity.status(404).body("Оператор не найден");
        }
        Task task = taskService.getEntityById(taskParams.getTaskId()).orElse(null);
        if (task == null) {
            return ResponseEntity.status(403).body("Задача не найдена");
        }
        task.setTaskDescription(taskParams.getTaskDescription() + " | " +
                task.getTaskDescription());
        task.setEndDate(LocalDateTime.now());
        task.setTaskStatus(taskParams.getTaskStatus());

        taskService.addTask(task);
        actionLogService.log(String.format("Успешно завершена задача %s оператором %s", taskParams.getTaskId(), taskParams.getOperatorId()), "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity
                .ok("200");
    }

    @PostMapping("operator/{operatorId}/tasks")
    public ResponseEntity<List<Task_DTO>> getTasksByOperator(@PathVariable Integer operatorId, @RequestBody String taskStatus) {
        if (operatorId == null) {
            operatorId = authUtils.getOperatorFromAuth().getOperatorId();
        }
        return ResponseEntity
                .ok(taskMapper
                        .toDTO(taskService.getAllTasksByParams(operatorId, null, null, taskStatus)));
    }



    /**
     * Method creates tasks from contacts depends on its status.
     * Parameters are describe task's count and types.
     * @return
     */
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("tasks/manager/create/auto")
    public ResponseEntity<Integer> autoCreateTasks(@RequestBody @NonNull List<TaskAutoCreationRequest> taskAutoCreationRequestList) {
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
                        .peek(System.out::println)
                        .map(taskMapper::toEntity)
                        .toList())
                .size()
        );
    }

    /*PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/tasks/manager/create")
    public ResponseEntity<Integer> createTasks(@RequestBody List<Task_DTO> requestTasks) {
        actionLogService.log(String.format("Попытка создать задач: %s ", requestTasks.size()), "INFO", authUtils.getEmployeeFromAuth());
        return ResponseEntity.ok(taskService.addAllTasks(taskListMapper.toEntity(requestTasks)).size());
    } */

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("task/manager/update/{taskId}")
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
    @PutMapping("task/operator/update/{taskId}")
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
    @PostMapping("tasks/delete")
    public ResponseEntity<?> deleteTasks(@RequestBody List<Long> requestTasksId) {
        actionLogService.log(String.format("Попытка удалить задач: %s ", requestTasksId.size()), "INFO", authUtils.getEmployeeFromAuth());
        for(Long taskId : requestTasksId) {
            taskService.deleteTask(taskId);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("tasks/status")
    public ResponseEntity<List<String>> getStatus() {
        return ResponseEntity.ok(taskService.getTaskStatus());
    }
}
