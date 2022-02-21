package com.example.scheduletesttask.repository;

import com.example.scheduletesttask.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}
