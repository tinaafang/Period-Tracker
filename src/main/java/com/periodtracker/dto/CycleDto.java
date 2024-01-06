package com.periodtracker.dto;

import java.util.Date;

public class CycleDto {
    private Date cycleStart;
    private Date cycleEnd;
    private Integer cycleLength;

    public Date getCycleStart() {
        return cycleStart;
    }

    public void setCycleStart(Date cycleStart) {
        this.cycleStart = cycleStart;
    }

    public Date getCycleEnd() {
        return cycleEnd;
    }

    public void setCycleEnd(Date cycleEnd) {
        this.cycleEnd = cycleEnd;
    }

    public Integer getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(Integer cycleLength) {
        this.cycleLength = cycleLength;
    }
}
