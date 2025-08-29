package com.capstone.com.vfr_backend.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddVASPackDto {
    private String packTitle;
    private String description;
    private double price;
    private LocalDate launchDate;
    private String serviceType;
    
}
