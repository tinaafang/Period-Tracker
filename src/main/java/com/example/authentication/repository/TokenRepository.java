package com.example.authentication.repository;

import com.example.authentication.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository
        extends JpaRepository<Token, Integer> {

    Token findByToken(String token);

    @Query("SELECT token from TOKEN token WHERE token.user.email = ?1")
    List<Token> findByEmail(String email);
}