package com.example.onlineordering.service;

import com.example.onlineordering.entity.ConfirmationToken;
import com.example.onlineordering.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        Optional<ConfirmationToken> confirmationToken = getToken(token);
        // TODO: throw
        if(confirmationToken.get() != null) {
            confirmationToken.get().setConfirmedAt(LocalDateTime.now());
            confirmationTokenRepository.save(confirmationToken.get());

        }
    }

}
