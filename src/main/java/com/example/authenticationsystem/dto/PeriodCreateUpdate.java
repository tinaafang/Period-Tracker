package com.example.authenticationsystem.dto;

import com.example.authenticationsystem.entity.Period;
import com.example.authenticationsystem.entity.User;

import java.util.Set;

public class PeriodCreateUpdate {
    private Set<Period> periods;

    private User user;

    public Set<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(Set<Period> periods) {
        this.periods = periods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
