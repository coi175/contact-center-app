package com.coi.contactcenterapp.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private String role;
}
