package com.example.onlineordering;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "CONFIRMATION_TOKEN")
@Table
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMT_NUM")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="USR_NUM", nullable=false, updatable=false)
    private User user;

    @Column(name="TOKEN",nullable = false)
    private String token;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "EXPIRED_AT")
    private LocalDateTime expiredAt;

    @Column(name = "CONFIRMED_AT")
    private LocalDateTime confirmedAt;


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
}
