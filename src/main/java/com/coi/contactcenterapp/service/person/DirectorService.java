package com.coi.contactcenterapp.service.person;


import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.repository.DirectorRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DirectorService implements BaseService<Director, Integer> {
    private final DirectorRepository directorRepository;

    @Override
    public Optional<Director> getEntityById(Integer directorId) {
        return directorRepository.findById(directorId);
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }
}
