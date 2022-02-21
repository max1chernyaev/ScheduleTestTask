package com.example.scheduletesttask.repository;

import com.example.scheduletesttask.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
