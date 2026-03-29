package com.justiceserve.backend.citizen.controller;

import com.justiceserve.backend.citizen.dto.CitizenDocumentRequestDto;
import com.justiceserve.backend.citizen.dto.CitizenDocumentResponseDto;
import com.justiceserve.backend.citizen.dto.CitizenDocumentVerificationStatusDto;
import com.justiceserve.backend.citizen.service.CitizenDocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitizenDocumentController {

    private final CitizenDocumentService citizenDocumentService;

    public CitizenDocumentController(CitizenDocumentService citizenDocumentService) {
        this.citizenDocumentService = citizenDocumentService;
    }

    @PostMapping("/api/citizens/{citizenId}/documents")
    @ResponseStatus(HttpStatus.CREATED)
    public CitizenDocumentResponseDto addDocument(@PathVariable Long citizenId,
                                                  @Valid @RequestBody CitizenDocumentRequestDto dto) {
        return citizenDocumentService.addDocument(citizenId, dto);
    }

    @GetMapping("/api/citizens/{citizenId}/documents")
    public List<CitizenDocumentResponseDto> getDocumentsByCitizenId(@PathVariable Long citizenId) {
        return citizenDocumentService.getDocumentsByCitizenId(citizenId);
    }

    @PutMapping("/api/documents/{documentId}/verification-status")
    public CitizenDocumentResponseDto updateVerificationStatus(
            @PathVariable Long documentId,
            @Valid @RequestBody CitizenDocumentVerificationStatusDto dto) {
        return citizenDocumentService.updateVerificationStatus(documentId, dto.getVerificationStatus());
    }
}