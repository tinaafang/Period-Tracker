package com.example.authenticationsystem.service;

import com.example.authenticationsystem.entity.Period;
import com.example.authenticationsystem.entity.User;
import com.example.authenticationsystem.exceptions.BadRequestException;
import com.example.authenticationsystem.repository.PeriodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class PeriodService {
    @Autowired
    UserService userService;

    @Autowired
    PeriodRepository periodRepository;


    public void validatePeriod(Period sourcePeriod) {
        Date now = new Date();

        if(sourcePeriod.getStartDate() == null) {
            throw new BadRequestException("Start date cannot be null");
        }
        if(sourcePeriod.getEndDate() == null) {
            throw new BadRequestException("End date cannot be null");
        }
        if(sourcePeriod.getStartDate().after(sourcePeriod.getEndDate())) {
            throw new BadRequestException("Start date cannot be later then the end date");
        }
        if(sourcePeriod.getStartDate().after(now)) {
            throw new BadRequestException("Start date cannot be future");
        }
        if(sourcePeriod.getUser().getId() == null) {
            throw new BadRequestException("User cannot be null");
        }
        // TODO: check for overlapping periods
        Optional<User> user = userService.getUserById(sourcePeriod.getUser().getId());
        if(user.isEmpty()) {
            throw new BadRequestException("User does not exist");
        }
    }
    public Period createPeriod(Period sourcePeriod) {
        validatePeriod(sourcePeriod);
        Period actualPeriod = new Period();
        actualPeriod.setUser(userService.getUserById(sourcePeriod.getUser().getId().get());
        actualPeriod.setStartDate(sourcePeriod.getStartDate());
        actualPeriod.setEndDate(sourcePeriod.getEndDate());
        actualPeriod.setNote(sourcePeriod.getNote());
        periodRepository.save(actualPeriod);
        return actualPeriod;
    }

    public Period updatePeriod(Period sourcePeriod) {
        validatePeriod(sourcePeriod);
        Period actualPeriod = new Period();
        actualPeriod.setStartDate(sourcePeriod.getStartDate());
        actualPeriod.setEndDate(sourcePeriod.getEndDate());
        actualPeriod.setNote(sourcePeriod.getNote());
        periodRepository.save(actualPeriod);
        return actualPeriod;
    }

    public void deletePeriod(Integer periodId) {
        Optional<Period> actualPeriod = periodRepository.findById(periodId);
        if(actualPeriod.isEmpty()) {
            throw new BadRequestException("Period does not exist");
        }
        periodRepository.delete(actualPeriod.get());
    }

    public Period getPeriod(Integer periodId) {
        Optional<Period> actualPeriod = periodRepository.findById(periodId);
        if(actualPeriod.isEmpty()) {
            throw new BadRequestException("Period does not exist");
        }
        return actualPeriod.get();
    }

    public Period getPeriodsByUserId(Integer userId) {

    }


}
