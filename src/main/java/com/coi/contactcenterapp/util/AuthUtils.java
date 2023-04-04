package com.coi.contactcenterapp.util;

import com.coi.contactcenterapp.domain.dto.auth.JwtAuthentication;
import com.coi.contactcenterapp.domain.entity.person.*;
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
    private static User user;

    public Director getDirectorFromAuth() {
        Director director = getEmployeeFromAuth().getDirector();
        if (director == null) {
            throw new EntityNotFoundException("Данные авторизации не принаджелат директору");
        }
        return director;
    }

    public Manager getManagerFromAuth() {
        Manager manager = getEmployeeFromAuth().getManager();
        if (manager == null) {
            throw new EntityNotFoundException("Данные авторизации не принаджелат менеджеру");
        }
        return manager;
    }

    public Operator getOperatorFromAuth() {
        Operator operator = getEmployeeFromAuth().getOperator();
        if (operator == null) {
            throw new EntityNotFoundException("Данные авторизации не принаджелат оператору");
        }
        return operator;
    }

    public Employee getEmployeeFromAuth() {
        Employee employee = getUserFromAuth().getEmployee();
        if (employee == null) {
            throw new EntityNotFoundException("Ошибка аккаунта, у авторизации нет связи с сотрудником");
        }
        return employee;
    }

    public User getUserFromAuth() {
        if (AuthUtils.user != null && getAuthUsername().equals(AuthUtils.user.getUsername())) {
            return AuthUtils.user;
        } else {
            return AuthUtils.user = userService.getByUsername(getAuthUsername()).orElseThrow(() -> new EntityNotFoundException("Данные авторизации не найдены"));
        }
    }

    private String getAuthUsername() {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return jwtAuthentication.getUsername();
    }
}
