package com.example.authenticationsystem.controller;

import com.example.authenticationsystem.dto.PeriodCreateUpdate;
import com.example.authenticationsystem.dto.PeriodSearch;
import com.example.authenticationsystem.repository.PeriodRepository;
import com.example.authenticationsystem.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PeriodService periodService;

    @GetMapping(path = "/search")
    public Object search(@RequestBody PeriodSearch periodSearch) {
        return periodRepository.search(periodSearch.getId(),periodSearch.getStartDate(), periodSearch.getEndDate());
    }

    @PostMapping()
    public Object createPeriods(@RequestBody PeriodCreateUpdate periodCreateUpdate) {
        return periodService.createPeriods(periodCreateUpdate);
    }


}
