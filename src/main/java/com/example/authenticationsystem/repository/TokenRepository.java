package com.example.authenticationsystem.repository;

import com.example.authenticationsystem.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository
        extends JpaRepository<Token, Integer> {

    Token findByToken(String token);

    @Query("SELECT token from tokens token WHERE token.user.email = ?1")
    List<Token> findByEmail(String email);
}