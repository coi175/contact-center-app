package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneCallRepository extends JpaRepository<PhoneCall, Long> {
    @Query("""
            SELECT c FROM PhoneCall c WHERE (:contactId IS NULL OR c.contact.contactId = :contactId)
            AND (:taskId IS NULL or c.task.taskId = :taskId) AND (:operatorId IS NULL or c.operator.operatorId = :operatorId)
            """)
    public List<PhoneCall> findPhoneCallsByParams(@Param("contactId") String contactId, @Param("taskId") Long taskId, @Param("operatorId") Integer operatorId);
}
