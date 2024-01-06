package com.periodtracker.controller;

import com.periodtracker.dto.CycleDto;
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
        ArrayList<CycleDto> cycleStatsJson = new ArrayList<>();
        cycleStats.forEach(cycleStat -> {
            if (cycleStat.get(0) != null && cycleStat.get(1) != null && cycleStat.get(2) != null) {
                CycleDto cycle = new CycleDto();
                cycle.setCycleEnd((Date) cycleStat.get(0));
                cycle.setCycleStart((Date) cycleStat.get(1));
                cycle.setCycleLength((Integer) cycleStat.get(2));
                cycleStatsJson.add(cycle);
            }
        });
        cycleStatsJson.sort(Comparator.comparing(CycleDto::getCycleStart));
        stats.put("cycleStats", cycleStatsJson);


        ArrayList<ArrayList> periodStats = periodRepository.getPeriodStats(userId,oneYearAgo);
//        Map<Long,Long> periodStatsJson = new HashMap<>();
//        periodStats.forEach(periodStat -> {
//            periodStatsJson.put((Long) periodStat.get(0),(Long) periodStat.get(1));
//        });
        stats.put("periodStats", periodStats);
        return stats;
    }

    @PostMapping()
    public Object createPeriods(@RequestBody PeriodCreateUpdateRequest periodCreateUpdateRequest) {
        return periodService.updatePeriods(periodCreateUpdateRequest);
    }
//
//    @GetMapping("/intervals/{userId}")
//    public Object getInterval(@PathVariable Integer userId) {
//
//        return intervals;
//    }


}
