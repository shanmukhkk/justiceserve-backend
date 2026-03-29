package com.justiceserve.backend.citizen.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CitizenResponseDto {

    private Long citizenId;
    private String name;
    private LocalDate dob;
    private String gender;
    private String address;
    private String contactInfo;
    private String status;
}