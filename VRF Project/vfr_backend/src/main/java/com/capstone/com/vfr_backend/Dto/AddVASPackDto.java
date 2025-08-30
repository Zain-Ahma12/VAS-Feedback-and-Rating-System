package com.capstone.com.vfr_backend.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddVASPackDto {
    private String packTitle;
    private String description;
    private double price;
    private LocalDate launchDate;
    private boolean status;
    private String serviceType;
    
}
