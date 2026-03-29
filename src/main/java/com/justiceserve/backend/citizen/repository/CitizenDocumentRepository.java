package com.justiceserve.backend.citizen.repository;

import com.justiceserve.backend.citizen.entity.CitizenDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitizenDocumentRepository extends JpaRepository<CitizenDocument, Long> {

    List<CitizenDocument> findByCitizenCitizenId(Long citizenId);
}