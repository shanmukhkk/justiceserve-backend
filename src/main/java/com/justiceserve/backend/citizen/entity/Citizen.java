package com.justiceserve.backend.citizen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "citizens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citizenId;

    @Column(nullable = false)
    private String name;

    private LocalDate dob;

    private String gender;

    private String address;

    private String contactInfo;

    private String status;
}