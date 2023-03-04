package com.coi.contactcenterapp.controller.calling;

import com.coi.contactcenterapp.domain.dto.auth.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    /*
    private final TaskService taskService;

    @GetMapping("task/{taskId}")
    public Task_DTO getTask(@PathVariable Long taskId) {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("manager/{managerId}/tasks")
    public ResponseEntity<List<Task_DTO>> getTasksToManager(@PathVariable Integer managerId) {

    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("operator/tasks")
    public ResponseEntity<List<Task_DTO>> getTasksToOperator() {

    } /*

    /**
     * Method creates tasks from contacts depends on its status.
     * Parameters are describe task's count and types.
     * @return
     */ /*
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("tasks/create/auto")
    public ResponseEntity<Integer> autoCreateTasks(@RequestBody autoTastCreationRequest) {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/tasks/manager/create")
    public ResponseEntity<Integer> createTasks(@RequestBody List<Task_DTO> requestTasks) {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PutMapping("/task/manager/update/{id}")
    public ResponseEntity<?> managerUpdateTask(@PathVariable Long id, @RequestBody Task_DTO requestTask) {

    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/task/operator/update/{id}")
    public ResponseEntity<?> operatorUpdateTask(@PathVariable Long id, @RequestBody Task_DTO requestTask) {

    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/tasks/delete")
    public ResponseEntity<?> deleteTasks(@RequestBody List<Long> requestTasksId) {

    } */
}
