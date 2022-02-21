package com.example.scheduletesttask.repository;

import com.example.scheduletesttask.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {



}
