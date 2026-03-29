package com.justiceserve.backend.citizen.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CitizenRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    private LocalDate dob;
    private String gender;
    private String address;
    private String contactInfo;
    private String status;
}