package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.auth.User;
import com.coi.contactcenterapp.domain.common.IntegerId;
import com.coi.contactcenterapp.repository.UserRepository;
import com.coi.contactcenterapp.service.common.AbstractService;
import org.springframework.stereotype.Service;


@Service
public class UserService extends AbstractService<User, IntegerId, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
