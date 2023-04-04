package com.coi.contactcenterapp.service.calling;

import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.repository.PhoneCallRepository;
import com.coi.contactcenterapp.repository.TaskRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService implements BaseService<Task, Long> {
    private final TaskRepository taskRepository;
    @Override
    public Optional<Task> getEntityById(Long id) {
        return taskRepository.findById(id);
    }
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> addAllTasks(List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<Task> getAllTasksByParams(Integer operatorId, Integer managerId, String contactId, String taskStatus) {
        List<Task> res = taskRepository.findAllByTasksByParams(operatorId, managerId, contactId, taskStatus);
        return res;
    }

    public List<String> getTaskStatus() {
        return taskRepository.findAllTaskStatus();
    }

    public List<Task> getSuccessTasks(Integer operatorId, LocalDate date) {
        return taskRepository.findAllSuccessTasksByDateAndOperator(operatorId,  LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MIN).plusDays(1));
    }

    public List<Task> getOtherTasks(Integer operatorId, LocalDate date) {
        return taskRepository.findAllOtherTasksByDateAndOperator(operatorId,  LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MIN).plusDays(1));
    }

}
