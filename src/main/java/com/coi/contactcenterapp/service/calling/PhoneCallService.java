package com.coi.contactcenterapp.service.calling;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.repository.ContactRepository;
import com.coi.contactcenterapp.repository.PhoneCallRepository;
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
public class PhoneCallService implements BaseService<PhoneCall, Long> {
    private final PhoneCallRepository phoneCallRepository;
    @Override
    public Optional<PhoneCall> getEntityById(Long id) {
        return phoneCallRepository.findById(id);
    }

    public List<PhoneCall> getPhoneCallsByParams(String contactId, Long taskId, Integer operatorId) {
        return phoneCallRepository.findPhoneCallsByParams(contactId, taskId, operatorId);
    }
    public PhoneCall addPhoneCall(PhoneCall phoneCall) {
        return phoneCallRepository.save(phoneCall);
    }

    public List<PhoneCall> getSuccessCalls(Integer operatorId, LocalDate date) {
        return phoneCallRepository.findAllSuccessCalls(operatorId, LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MIN).plusDays(1));
    }
    public List<PhoneCall> getOtherCalls(Integer operatorId, LocalDate date) {
        return phoneCallRepository.findAllOtherCalls(operatorId, LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MIN).plusDays(1));
    }
    public Integer getAverageCallDuration(Integer operatorId, LocalDate date) {
        Integer count = phoneCallRepository.findAverageCallDuration(operatorId, LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MIN).plusDays(1));
        return count == null ? 0 : count;
    }
}
