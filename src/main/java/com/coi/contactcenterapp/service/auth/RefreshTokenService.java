package com.coi.contactcenterapp.service.auth;

import com.coi.contactcenterapp.domain.entity.auth.RefreshToken;
import com.coi.contactcenterapp.repository.RefreshTokenRepository;
import com.coi.contactcenterapp.service.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements BaseService<RefreshToken, Integer> {
    private final RefreshTokenRepository repository;

    public Optional<RefreshToken> getByUsername(String username) {
        return repository.findRefreshTokenByUsername(username);
    }

    public RefreshToken addEntity(RefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> getEntityById(Integer id) {
        return repository.findById(id);
    }

    public RefreshToken deleteByUsername(String username) {
        return repository.deleteAllByUsername(username);
    }

    public void delete(RefreshToken token) {
        repository.delete(token);
    }
    public Optional<RefreshToken> getTokenByToken(String token) {
        return repository.findRefreshTokenByToken(token);
    }
}
