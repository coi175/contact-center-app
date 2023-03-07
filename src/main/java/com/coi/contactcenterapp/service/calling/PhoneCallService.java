package com.coi.contactcenterapp.service.calling;

import com.coi.contactcenterapp.domain.entity.calling.Contact;
import com.coi.contactcenterapp.domain.entity.calling.PhoneCall;
import com.coi.contactcenterapp.repository.ContactRepository;
import com.coi.contactcenterapp.repository.PhoneCallRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
