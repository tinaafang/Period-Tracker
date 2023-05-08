package com.example.recipewebsite.service;

import com.example.recipewebsite.entity.Token;
import com.example.recipewebsite.exceptions.BadRequestException;
import com.example.recipewebsite.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    public String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Token getToken(String tokenStr) {
        Token token = tokenRepository.findByToken(tokenStr);
        if(token== null) {
            throw new BadRequestException("token not found");
        }
        return token;
    }

    public List<Token> getTokensByEmail(String email) {
        return tokenRepository.findByEmail(email);
    }

    public void setConfirmedAt(String tokenStr) {
        Token confirmationToken = getToken(tokenStr);
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenRepository.save(confirmationToken);
    }

}
