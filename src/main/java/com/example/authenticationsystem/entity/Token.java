package com.example.authenticationsystem.entity;

import com.example.authenticationsystem.enums.TokenPurpose;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "tokens")
@Table
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false, updatable=false)
    private User user;

    @Column(name="TOKEN",nullable = false)
    private String token;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "EXPIRED_AT")
    private LocalDateTime expiredAt;

    @Column(name = "CONFIRMED_AT")
    private LocalDateTime confirmedAt;

    @Column(name="PURPOSE")
    private TokenPurpose purpose;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public TokenPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(TokenPurpose purpose) {
        this.purpose = purpose;
    }
}
