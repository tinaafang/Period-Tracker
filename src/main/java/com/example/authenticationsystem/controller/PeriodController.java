package com.example.authenticationsystem.controller;

import com.example.authenticationsystem.dto.Cycle;
import com.example.authenticationsystem.dto.PeriodCreateUpdate;
import com.example.authenticationsystem.dto.PeriodSearch;
import com.example.authenticationsystem.repository.PeriodRepository;
import com.example.authenticationsystem.service.PeriodService;
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

    @GetMapping(path = "/stats/{userId}")
    public Object getStats(@PathVariable Integer userId) {
        Map<String, Object> stats = new HashMap<>();

        ArrayList<ArrayList> cycleStats = periodRepository.getCycleStats(userId);
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


        ArrayList<ArrayList> periodStats = periodRepository.getPeriodStats(userId);
        Map<Integer,Integer> periodStatsJson = new HashMap<>();
        periodStats.forEach(periodStat -> {
            periodStatsJson.put((Integer) periodStat.get(0),(Integer) periodStat.get(1));
        });
        stats.put("periodStats", periodStatsJson);
        return stats;
    }

    @PostMapping()
    public Object createPeriods(@RequestBody PeriodCreateUpdate periodCreateUpdate) {
        return periodService.updatePeriods(periodCreateUpdate);
    }
//
//    @GetMapping("/intervals/{userId}")
//    public Object getInterval(@PathVariable Integer userId) {
//
//        return intervals;
//    }


}
