package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
