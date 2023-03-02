package com.coi.contactcenterapp.repository;

import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCallRepository extends JpaRepository<PhoneCall, Long> {
}
