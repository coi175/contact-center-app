package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

    import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
            SELECT c FROM Task c WHERE (:operatorId IS NULL or c.operator.operatorId = :operatorId)
            AND (:managerId IS NULL or c.manager.managerId = :managerId) 
            AND (:contactId IS NULL or c.contact.contactId = :contactId)
            AND (:taskStatus IS NULL or c.taskStatus = :taskStatus)
            """)
    List<Task> findAllByTasksByParams(@Nullable @Param("operatorId") Integer operatorId, @Nullable @Param("managerId") Integer managerId,
                                      @Nullable @Param("contactId") String contactId, @Nullable @Param("taskStatus") String taskStatus);

    @Query("""
            SELECT DISTINCT(c.taskStatus) FROM Task c
            """)
    List<String> findAllTaskStatus();


    @Query("""
            SELECT c FROM Task c WHERE (c.operator.operatorId = :operatorId)
            AND (c.taskStatus = 'SUCCESS')
            AND (c.endDate >= :date1 AND c.endDate <= :date2)
            """)
    List<Task> findAllSuccessTasksByDateAndOperator(@Param("operatorId") Integer operatorId, @Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);

    @Query("""
            SELECT c FROM Task c WHERE (c.operator.operatorId = :operatorId)
            AND (c.taskStatus <> 'SUCCESS')
            AND (c.endDate >= :date1 AND c.endDate <= :date2)
            """)
    List<Task> findAllOtherTasksByDateAndOperator(@Param("operatorId") Integer operatorId, @Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);

}
