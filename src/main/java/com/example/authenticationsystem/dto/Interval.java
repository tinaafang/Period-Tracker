package com.example.authenticationsystem.dto;
import java.util.Date;

public class Interval {
    private Date intervalStart;
    private Date intervalEnd;
    private Integer interval;

    public Date getIntervalStart() {
        return intervalStart;
    }

    public void setIntervalStart(Date intervalStart) {
        this.intervalStart = intervalStart;
    }

    public Date getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(Date intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
