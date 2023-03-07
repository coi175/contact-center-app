package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> deleteAllByTaskId(List<Long> taskIdList);

    @Query("""
            SELECT c FROM Task c WHERE (:operatorId IS NULL OR c.operator.operatorId = :operatorId)
            AND (:managerId IS NULL OR c.manager.managerId = :managerId) 
            AND (:contactId IS NULL or c.contact.contactId = :operatorId)
            AND (:taskStatus IS NULL or c.taskStatus = :taskStatus)
            """)
    public List<Task> findAllByTasksByParams(@Param("operatorId") Integer operatorId, @Param("managerId") Integer managerId,
                                             @Param("contactId") String contactId, @Param("taskStatus") String taskStatus);
}
