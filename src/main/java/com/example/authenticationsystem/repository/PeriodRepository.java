package com.example.authenticationsystem.repository;

import com.example.authenticationsystem.entity.Period;
import com.example.authenticationsystem.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public class PeriodRepository extends JpaRepository<Period, Integer> {


}
