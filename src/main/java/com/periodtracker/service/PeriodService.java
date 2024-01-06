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
//        if(sourcePeriod.getStartDate().isAfter(sourcePeriod.getEndDate())) {
//            throw new BadRequestException(String.format("Start date (%s) cannot be later then the end date (%s)", sourcePeriod.getStartDate().toString(), sourcePeriod.getEndDate().toString()));
//        }
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
//        Set<Period> periodsBefore = user.getPeriods();
        periodCreateUpdateRequest.getPeriods().forEach(period -> {
            validateDates(period);
            Period actualPeriod = new Period();
            actualPeriod.setUser(user);
            actualPeriod.setCreatedAt(now);
            updateCommonProperties(period, actualPeriod);
            periodRepository.save(actualPeriod);
            actualPeriods.add(actualPeriod);
        });
//        periodRepository.deleteAll(periodsBefore);
        return actualPeriods;
    }

    public void updateCommonProperties(Period sourcePeriod, Period actualPeriod) {
        actualPeriod.setStartDate(sourcePeriod.getStartDate());
        actualPeriod.setEndDate(sourcePeriod.getEndDate());
        actualPeriod.setNote(sourcePeriod.getNote());
    }


//    public Set<Period> updatePeriods(PeriodCreateUpdate periodCreateUpdate) {
//        if(periodCreateUpdate.getUser().getId() == null) {
//            throw new BadRequestException("User cannot be null");
//        }
//        if(periodCreateUpdate.getPeriods() == null) {
//            throw new BadRequestException("Periods cannot be null");
//        }
//
//        User user = userService.getUserById(periodCreateUpdate.getUser().getId());
//        Set<Period> actualPeriods = new HashSet<>();
//        Set<Period> periods = periodCreateUpdate.getPeriods();
//        if(!periods.isEmpty()) {
//            periods.forEach(period -> {
//                validateDates(period);
//
//                Optional<Period> actualPeriod = periodRepository.findById(period.getId());
//                actualPeriod.setUser(user);
//                updateCommonProperties(period,actualPeriod);
//                periodRepository.save(actualPeriod);
//                actualPeriods.add(actualPeriod);
//            });
//        }
//        return actualPeriods;
//    }

//    public Period updatePeriods(Period sourcePeriod) {
//        validateFields(sourcePeriod);
//        Optional<Period> period = periodRepository.findById(sourcePeriod.getId());
//        if(period.isEmpty()) {
//            throw new BadRequestException("Period does not exist");
//        }
//        Period actualPeriod = period.get();
//        actualPeriod.setStartDate(sourcePeriod.getStartDate());
//        actualPeriod.setEndDate(sourcePeriod.getEndDate());
//        actualPeriod.setNote(sourcePeriod.getNote());
//        periodRepository.save(actualPeriod);
//        return actualPeriod;
//    }

    public void deletePeriod(Integer periodId) {
        Optional<Period> actualPeriod = periodRepository.findById(periodId);
        if (actualPeriod.isEmpty()) {
            throw new BadRequestException("Period does not exist");
        }
        periodRepository.delete(actualPeriod.get());
    }

    public Period getPeriod(Integer periodId) {
        Optional<Period> actualPeriod = periodRepository.findById(periodId);
        if (actualPeriod.isEmpty()) {
            throw new BadRequestException("Period does not exist");
        }
        return actualPeriod.get();
    }

//    public Period getPeriodsByUserId(Integer userId) {
//
//    }


}
