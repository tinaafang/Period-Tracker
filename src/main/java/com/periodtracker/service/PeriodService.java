package com.periodtracker.service;

import com.periodtracker.dto.PeriodCreateUpdateRequest;
import com.periodtracker.entity.Period;
import com.periodtracker.entity.User;
import com.periodtracker.exceptions.BadRequestException;
import com.periodtracker.repository.PeriodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PeriodService {
    @Autowired
    UserService userService;

    @Autowired
    PeriodRepository periodRepository;


    public void validateDates(Period sourcePeriod) {
        LocalDate now = LocalDate.now();

        if (sourcePeriod.getStartDate() == null) {
            throw new BadRequestException("Start date cannot be null");
        }
        if (sourcePeriod.getEndDate() == null) {
            throw new BadRequestException("End date cannot be null");
        }

        if (sourcePeriod.getStartDate().isAfter(now)) {
            throw new BadRequestException(String.format("Start date (%s) cannot be in the future", sourcePeriod.getStartDate().toString()));
        }
    }

    public Set<Period> updatePeriods(PeriodCreateUpdateRequest periodCreateUpdateRequest) {
        Integer userId = periodCreateUpdateRequest.getUser().getId();
        LocalDateTime now = LocalDateTime.now();
        if (userId == null) {
            throw new BadRequestException("User cannot be null");
        }

        User user = userService.getUserById(userId);
        Set<Period> actualPeriods = new HashSet<>();
        periodRepository.deletePrevPeriods(now, userId);
        periodCreateUpdateRequest.getPeriods().forEach(period -> {
            validateDates(period);
            Period actualPeriod = new Period();
            actualPeriod.setUser(user);
            actualPeriod.setCreatedAt(now);
            updateCommonProperties(period, actualPeriod);
            periodRepository.save(actualPeriod);
            actualPeriods.add(actualPeriod);
        });
        return actualPeriods;
    }

    public void updateCommonProperties(Period sourcePeriod, Period actualPeriod) {
        actualPeriod.setStartDate(sourcePeriod.getStartDate());
        actualPeriod.setEndDate(sourcePeriod.getEndDate());
        actualPeriod.setNote(sourcePeriod.getNote());
    }

}
