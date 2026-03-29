package com.justiceserve.backend.citizen.service;

import com.justiceserve.backend.citizen.dto.CitizenDocumentRequestDto;
import com.justiceserve.backend.citizen.dto.CitizenDocumentResponseDto;
import com.justiceserve.backend.citizen.entity.Citizen;
import com.justiceserve.backend.citizen.entity.CitizenDocument;
import com.justiceserve.backend.citizen.repository.CitizenDocumentRepository;
import com.justiceserve.backend.citizen.repository.CitizenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenDocumentService {

    private final CitizenDocumentRepository citizenDocumentRepository;
    private final CitizenRepository citizenRepository;

    public CitizenDocumentService(CitizenDocumentRepository citizenDocumentRepository,
                                  CitizenRepository citizenRepository) {
        this.citizenDocumentRepository = citizenDocumentRepository;
        this.citizenRepository = citizenRepository;
    }

    public CitizenDocumentResponseDto addDocument(Long citizenId, CitizenDocumentRequestDto dto) {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + citizenId));

        CitizenDocument document = CitizenDocument.builder()
                .citizen(citizen)
                .docType(dto.getDocType())
                .fileUri(dto.getFileUri())
                .uploadedDate(dto.getUploadedDate() != null ? dto.getUploadedDate() : LocalDate.now())
                .verificationStatus(dto.getVerificationStatus() != null ? dto.getVerificationStatus() : "PENDING")
                .build();

        CitizenDocument savedDocument = citizenDocumentRepository.save(document);
        return mapToResponse(savedDocument);
    }

    public List<CitizenDocumentResponseDto> getDocumentsByCitizenId(Long citizenId) {
        return citizenDocumentRepository.findByCitizenCitizenId(citizenId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CitizenDocumentResponseDto updateVerificationStatus(Long documentId, String verificationStatus) {
        CitizenDocument document = citizenDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + documentId));

        document.setVerificationStatus(verificationStatus);

        CitizenDocument updatedDocument = citizenDocumentRepository.save(document);
        return mapToResponse(updatedDocument);
    }

    private CitizenDocumentResponseDto mapToResponse(CitizenDocument document) {
        return CitizenDocumentResponseDto.builder()
                .documentId(document.getDocumentId())
                .citizenId(document.getCitizen().getCitizenId())
                .docType(document.getDocType())
                .fileUri(document.getFileUri())
                .uploadedDate(document.getUploadedDate())
                .verificationStatus(document.getVerificationStatus())
                .build();
    }
}