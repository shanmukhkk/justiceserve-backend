package com.justiceserve.backend.citizen.repository;

import com.justiceserve.backend.citizen.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
}