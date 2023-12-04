package com.example.authenticationsystem.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Entity(name = "periods")
@Table
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERIOD_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false, updatable=false)
    private User user;

    @Column(name="START_DATE",nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    @Column(name="END_DATE")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;

    @Column(name="NOTE")
    private String note;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
