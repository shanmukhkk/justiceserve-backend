package com.justiceserve.backend.citizen.controller;

import com.justiceserve.backend.citizen.dto.CitizenRequestDto;
import com.justiceserve.backend.citizen.dto.CitizenResponseDto;
import com.justiceserve.backend.citizen.service.CitizenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitizenResponseDto createCitizen(@Valid @RequestBody CitizenRequestDto dto) {
        return citizenService.createCitizen(dto);
    }

    @GetMapping
    public List<CitizenResponseDto> getAllCitizens() {
        return citizenService.getAllCitizens();
    }

    @GetMapping("/{id}")
    public CitizenResponseDto getCitizenById(@PathVariable Long id) {
        return citizenService.getCitizenById(id);
    }

    @PutMapping("/{id}")
    public CitizenResponseDto updateCitizen(@PathVariable Long id,
                                            @Valid @RequestBody CitizenRequestDto dto) {
        return citizenService.updateCitizen(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteCitizen(@PathVariable Long id) {
        citizenService.deleteCitizen(id);
        return "Citizen deleted successfully";
    }
}