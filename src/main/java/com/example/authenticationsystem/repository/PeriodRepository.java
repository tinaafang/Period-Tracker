package com.example.authenticationsystem.repository;

import com.example.authenticationsystem.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PeriodRepository extends JpaRepository<Period, Integer> {

    Optional<Period> findById(Integer id);

    @Query("""
            SELECT period from periods period 
            WHERE period.user.id = :userId 
            and period.startDate <= :endDate
            and period.endDate >= :startDate
            """)
    List<Period> search(Integer userId, LocalDate startDate, LocalDate endDate);
}
