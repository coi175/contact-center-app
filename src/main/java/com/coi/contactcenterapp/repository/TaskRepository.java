package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
