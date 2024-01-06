package com.periodtracker.repository;

import com.periodtracker.entity.Token;
import com.periodtracker.enums.TokenPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository
        extends JpaRepository<Token, Integer> {

    Token findByToken(String token);

    @Query("SELECT token from tokens token WHERE token.user.email = ?1")
    List<Token> findByEmail(String email);

    @Query("SELECT token.id from tokens token WHERE token.user.email = ?1 and token.purpose = ?2 and token.expiredAt > ?3")
    List<Integer> findValidPasswordResetToken(String email, TokenPurpose passwordReset, LocalDateTime now);
}