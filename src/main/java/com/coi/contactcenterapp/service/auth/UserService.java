package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public Optional<User> getByUsername(@NonNull String username) {
        return Optional.empty();
    }

}
