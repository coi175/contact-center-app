package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.entity.person.User;
import com.coi.contactcenterapp.repository.UserRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User, Integer> {
    private final UserRepository repository;

    public Optional<User> getByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public Optional<User> getEntityById(Integer id) {
        return repository.findById(id);
    }

    public User addUser(User user) {
        return repository.save(user);
    }
}
