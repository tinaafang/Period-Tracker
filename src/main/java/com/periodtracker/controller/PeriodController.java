package com.periodtracker.controller;

import com.periodtracker.dto.Cycle;
import com.periodtracker.dto.PeriodCreateUpdateRequest;
import com.periodtracker.dto.PeriodSearchRequest;
import com.periodtracker.repository.PeriodRepository;
import com.periodtracker.service.PeriodService;
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
    public Object search(@RequestBody PeriodSearchRequest periodSearchRequest) {
        return periodRepository.search(periodSearchRequest.getUserId());
    }

    @GetMapping(path = "/stats/{userId}")
    public Object getStats(@PathVariable Integer userId) {
        Map<String, Object> stats = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = new Date(String.valueOf(calendar.getTime()));

        ArrayList<ArrayList> cycleStats = periodRepository.getCycleStats(userId,oneYearAgo);
        ArrayList<Cycle> cycleStatsJson = new ArrayList<>();
        cycleStats.forEach(cycleStat -> {
            if (cycleStat.get(0) != null && cycleStat.get(1) != null && cycleStat.get(2) != null) {
                Cycle cycle = new Cycle();
                cycle.setCycleEnd((Date) cycleStat.get(0));
                cycle.setCycleStart((Date) cycleStat.get(1));
                cycle.setCycleLength((Integer) cycleStat.get(2));
                cycleStatsJson.add(cycle);
            }
        });
        cycleStatsJson.sort(Comparator.comparing(Cycle::getCycleStart));
        stats.put("cycleStats", cycleStatsJson);

        ArrayList<ArrayList> periodStats = periodRepository.getPeriodStats(userId,oneYearAgo);
        stats.put("periodStats", periodStats);
        return stats;
    }

    @PostMapping()
    public Object createPeriods(@RequestBody PeriodCreateUpdateRequest periodCreateUpdateRequest) {
        return periodService.updatePeriods(periodCreateUpdateRequest);
    }

}
