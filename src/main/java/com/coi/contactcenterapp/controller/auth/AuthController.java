package com.coi.contactcenterapp.controller.auth;

import com.coi.contactcenterapp.domain.dto.auth.*;
import com.coi.contactcenterapp.exception.AuthException;
import com.coi.contactcenterapp.exception.RegisterException;
import com.coi.contactcenterapp.service.auth.AuthService;
import com.coi.contactcenterapp.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthUtils authUtils;

    //@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getRole() == null) {
            switch (authUtils.getUserFromAuth().getRole().getRoleId()) {
                case "ADMIN" -> {
                    registerRequest.setRole("MODERATOR");
                }
                case "MODERATOR" -> {
                    registerRequest.setRole("USER");
                }
                default -> {
                    throw new RegisterException("Невозможно зарегистрировать аккаунт с данными правами");
                }
            }
        }


        try {
            authService.register(registerRequest);
        } catch (RegisterException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
        return ResponseEntity.ok("200");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) {
        try {
            final JwtResponse token = authService.login(authRequest);
            return ResponseEntity.ok(token);
        } catch (AuthException e) {
            return ResponseEntity.status(403).body("Неверный логин и/или пароль");
        }
    }
    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        if (token.getAccessToken() == null) {
            return ResponseEntity.status(401).body(token);
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
