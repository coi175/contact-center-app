package com.coi.contactcenterapp.service.person;

import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.repository.ManagerRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService implements BaseService<Manager, Integer> {
    private final ManagerRepository managerRepository;

    @Override
    public Optional<Manager> getEntityById(Integer managerId) {
        return managerRepository.findById(managerId);
    }

    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }
}
