package com.example.authenticationsystem.entity;
import jakarta.persistence.*;
import java.util.Date;


@Entity(name = "tokens")
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
    private Date startDate;

    @Column(name="END_DATE")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
