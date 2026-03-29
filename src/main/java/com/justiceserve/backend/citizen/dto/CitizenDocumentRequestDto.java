package com.justiceserve.backend.citizen.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CitizenDocumentRequestDto {

    @NotBlank(message = "Document type is required")
    private String docType;

    @NotBlank(message = "File URI is required")
    private String fileUri;

    private LocalDate uploadedDate;
    private String verificationStatus;
}