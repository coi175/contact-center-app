package com.coi.contactcenterapp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenStorage {
    private String token;
    private User user;
    private Date date;
}
