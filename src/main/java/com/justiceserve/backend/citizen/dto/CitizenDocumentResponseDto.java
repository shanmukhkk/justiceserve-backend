package com.justiceserve.backend.citizen.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CitizenDocumentResponseDto {

    private Long documentId;
    private Long citizenId;
    private String docType;
    private String fileUri;
    private LocalDate uploadedDate;
    private String verificationStatus;
}