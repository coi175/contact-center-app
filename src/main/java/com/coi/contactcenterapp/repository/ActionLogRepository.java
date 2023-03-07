package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.info.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {
    public List<ActionLog> findAllByEmployeeEmployeeId(Integer employeeId);
}
