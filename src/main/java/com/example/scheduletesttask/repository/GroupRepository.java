package com.example.scheduletesttask.repository;

import com.example.scheduletesttask.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
