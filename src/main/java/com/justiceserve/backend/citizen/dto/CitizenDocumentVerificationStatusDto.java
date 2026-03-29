package com.justiceserve.backend.citizen.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CitizenDocumentVerificationStatusDto {

    @NotBlank(message = "Verification status is required")
    private String verificationStatus;
}