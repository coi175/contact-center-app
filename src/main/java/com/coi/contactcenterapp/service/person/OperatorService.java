package com.coi.contactcenterapp.service.person;

import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.repository.OperatorRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OperatorService implements BaseService<Operator, Integer> {
    private final OperatorRepository operatorRepository;

    @Override
    public Optional<Operator> getEntityById(Integer operatorId) {
        return operatorRepository.findById(operatorId);
    }

    public Operator save(Operator operator) {
        return operatorRepository.save(operator);
    }
}
