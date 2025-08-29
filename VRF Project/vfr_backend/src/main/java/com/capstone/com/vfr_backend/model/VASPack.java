package com.capstone.com.vfr_backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VASPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pack_id")
    private Long packId;

    @NotBlank(message = "Pack title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(name = "pack_title", nullable = false, length = 100)
    private String packTitle;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Price is required")
    @Column(name = "price", nullable = false)
    private double price;

    @NotBlank(message = "Launch Date is required")
    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;

    @NotBlank(message = "Serivce type is required")
    @Column(name = "service_type", nullable = false)
    private String serviceType;
    
    // One VASPack can have many Feedback
    @OneToMany(mappedBy="vasPack", orphanRemoval = true) // inverse side
    private List<Feedback> feedbacks = new ArrayList<>();
}
