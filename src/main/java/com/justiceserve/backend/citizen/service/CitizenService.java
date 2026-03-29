package com.justiceserve.backend.citizen.service;

import com.justiceserve.backend.citizen.dto.CitizenRequestDto;
import com.justiceserve.backend.citizen.dto.CitizenResponseDto;
import com.justiceserve.backend.citizen.entity.Citizen;
import com.justiceserve.backend.citizen.repository.CitizenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public CitizenResponseDto createCitizen(CitizenRequestDto dto) {
        Citizen citizen = Citizen.builder()
                .name(dto.getName())
                .dob(dto.getDob())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .contactInfo(dto.getContactInfo())
                .status(dto.getStatus())
                .build();

        Citizen savedCitizen = citizenRepository.save(citizen);
        return mapToResponse(savedCitizen);
    }

    public List<CitizenResponseDto> getAllCitizens() {
        return citizenRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CitizenResponseDto getCitizenById(Long id) {
        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + id));

        return mapToResponse(citizen);
    }

    public CitizenResponseDto updateCitizen(Long id, CitizenRequestDto dto) {
        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + id));

        citizen.setName(dto.getName());
        citizen.setDob(dto.getDob());
        citizen.setGender(dto.getGender());
        citizen.setAddress(dto.getAddress());
        citizen.setContactInfo(dto.getContactInfo());
        citizen.setStatus(dto.getStatus());

        Citizen updatedCitizen = citizenRepository.save(citizen);
        return mapToResponse(updatedCitizen);
    }

    public void deleteCitizen(Long id) {
        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Citizen not found with id: " + id));

        citizenRepository.delete(citizen);
    }

    private CitizenResponseDto mapToResponse(Citizen citizen) {
        return CitizenResponseDto.builder()
                .citizenId(citizen.getCitizenId())
                .name(citizen.getName())
                .dob(citizen.getDob())
                .gender(citizen.getGender())
                .address(citizen.getAddress())
                .contactInfo(citizen.getContactInfo())
                .status(citizen.getStatus())
                .build();
    }
}