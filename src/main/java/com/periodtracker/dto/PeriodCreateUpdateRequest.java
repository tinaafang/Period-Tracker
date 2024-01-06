package com.periodtracker.dto;

import com.periodtracker.entity.Period;
import com.periodtracker.entity.User;

import java.util.Set;

public class PeriodCreateUpdateRequest {
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
