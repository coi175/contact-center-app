package com.coi.contactcenterapp.service.calling;

import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.domain.entity.calling.Task;
import com.coi.contactcenterapp.repository.PhoneCallRepository;
import com.coi.contactcenterapp.repository.TaskRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Task> addAllTasks(List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }

    public List<Task> deleteAllTasksById(List<Long> taskIdList) {
        return taskRepository.deleteAllByTaskId(taskIdList);
    }

    public List<Task> getAllTasksByParams(Integer operatorId, Integer managerId, String contactId, String taskStatus) {
        return taskRepository.findAllByTasksByParams(operatorId, managerId, contactId, taskStatus);
    }
}
