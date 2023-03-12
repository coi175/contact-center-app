package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.dto.auth.JwtAuthentication;
import com.coi.contactcenterapp.domain.dto.auth.JwtRequest;
import com.coi.contactcenterapp.domain.dto.auth.JwtResponse;
import com.coi.contactcenterapp.domain.dto.auth.RegisterRequest;
import com.coi.contactcenterapp.domain.entity.auth.RefreshToken;
import com.coi.contactcenterapp.domain.entity.info.Role;
import com.coi.contactcenterapp.domain.entity.person.*;
import com.coi.contactcenterapp.exception.AuthException;
import com.coi.contactcenterapp.exception.EntityNotFoundException;
import com.coi.contactcenterapp.exception.RegisterException;
import com.coi.contactcenterapp.util.AuthUtils;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service responsible for auth by password and replace dead access and refresh tokens
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    private final AuthUtils authUtils;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public void register(@NonNull RegisterRequest registerRequest) {
        User user = userService.getByUsername(registerRequest.getUsername()).orElse(null);
        if (user != null) {
            throw new RegisterException("Пользователь с таким никнеймом уже существует");
        }
        Role role = roleService.getEntityById(registerRequest.getRole())
                .orElseThrow(() -> new EntityNotFoundException("Сущность Role с id=" + registerRequest.getRole() + " не найдена"));

        // create user
        user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()), role);
        // create employee
        Employee employee = new Employee(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getEmail());
        // create employee by role
        switch (role.getRoleId()) {
            case "ADMIN"  -> {
                Director director = new Director();
                employee.setDirector(director);
            }
            case "MODERATOR" -> {
                Manager manager = new Manager();
                manager.setDirector(authUtils.getDirectorFromAuth());
                employee.setManager(manager);
            }
            case "USER" -> {
                Operator operator = new Operator();
                operator.setManager(authUtils.getManagerFromAuth());
                employee.setOperator(operator);
            }
        }
        user.setEmployee(employee);
        userService.addUser(user);
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByUsername(authRequest.getUsername())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));

        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            RefreshToken refreshTokenEntity = refreshTokenService.getByUsername(user.getUsername()).orElse(new RefreshToken(refreshToken, user.getUsername(), LocalDateTime.now()));
            refreshTokenEntity.setToken(refreshToken);
            refreshTokenEntity.setDateTime(LocalDateTime.now());
            refreshTokenService.addEntity(refreshTokenEntity);
            return new JwtResponse(accessToken, refreshToken, user.getRole().getRoleId());
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final RefreshToken saveRefreshToken = refreshTokenService.getByUsername(username).orElse(null); // имя пользователя а не id
            if (saveRefreshToken != null && saveRefreshToken.getToken().equals(refreshToken)) {
                final User user = userService.getByUsername(username)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null, user.getRole().getRoleId());
            }
        }
        return new JwtResponse(null, null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final RefreshToken saveRefreshToken = refreshTokenService.getByUsername(username).orElse(null);
            if (saveRefreshToken != null && saveRefreshToken.getToken().equals(refreshToken)) {
                final User user = userService.getByUsername(username)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                refreshTokenService.delete(saveRefreshToken);
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                // update refresh token in storage
                saveRefreshToken.setToken(newRefreshToken);
                saveRefreshToken.setDateTime(LocalDateTime.now());
                refreshTokenService.addEntity(saveRefreshToken);

                return new JwtResponse(accessToken, newRefreshToken, user.getRole().getRoleId());
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
