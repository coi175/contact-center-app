package com.coi.contactcenterapp.util;

import com.coi.contactcenterapp.domain.dto.auth.JwtAuthentication;
import com.coi.contactcenterapp.domain.entity.person.Director;
import com.coi.contactcenterapp.domain.entity.person.Manager;
import com.coi.contactcenterapp.domain.entity.person.Operator;
import com.coi.contactcenterapp.domain.entity.person.User;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthUtils {
    private final UserService userService;

    public Director getDirectorFromAuth() {
        Director director = getUserFromAuth().getEmployee().getDirector();
        if (director == null) {
            throw new EntityNotFoundException("Данные авторизации не принаджелат директору");
        }
        return director;
    }

    public Manager getManagerFromAuth() {
        Manager manager = getUserFromAuth().getEmployee().getManager();
        if (manager == null) {
            throw new EntityNotFoundException("Данные авторизации не принаджелат менеджеру");
        }
        return manager;
    }

    public Operator getOperatorFromAuth() {
        Operator operator = getUserFromAuth().getEmployee().getOperator();
        if (operator == null) {
            throw new EntityNotFoundException("Данные авторизации не принаджелат оператору");
        }
        return operator;
    }

    private User getUserFromAuth() {
        return userService.getByUsername(getAuthUsername()).orElseThrow(() -> new EntityNotFoundException("Данные авторизации не найдены"));
    }

    private String getAuthUsername() {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return jwtAuthentication.getUsername();
    }
}
