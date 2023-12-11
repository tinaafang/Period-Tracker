package com.example.authenticationsystem.controller;

import com.example.authenticationsystem.dto.Interval;
import com.example.authenticationsystem.dto.PeriodCreateUpdate;
import com.example.authenticationsystem.dto.PeriodSearch;
import com.example.authenticationsystem.repository.PeriodRepository;
import com.example.authenticationsystem.service.PeriodService;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PeriodService periodService;

    @PostMapping(path = "/search")
    public Object search(@RequestBody PeriodSearch periodSearch) {
        return periodRepository.search(periodSearch.getUserId());
    }

    @PostMapping()
    public Object createPeriods(@RequestBody PeriodCreateUpdate periodCreateUpdate) {
        return periodService.updatePeriods(periodCreateUpdate);
    }

    @GetMapping("/intervals/{userId}")
    public Object getInterval(@PathVariable Integer userId) {
        ArrayList<ArrayList> results =  periodRepository.getInterval(userId);
        ArrayList<Interval> intervals = new ArrayList<>();
        results.forEach(result -> {
            if(result.get(0) != null && result.get(1) != null && result.get(2) != null) {
                Interval interval = new Interval();
                interval.setIntervalEnd((Date) result.get(0));
                interval.setIntervalStart((Date) result.get(1));
                interval.setInterval((Integer) result.get(2));
                intervals.add(interval);
            }
        });
        intervals.sort(Comparator.comparing(Interval::getIntervalStart));
        return intervals;
    }


}
