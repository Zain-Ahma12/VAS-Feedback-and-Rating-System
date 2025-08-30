package com.capstone.com.vfr_backend.Dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VASPackDto {
    private Long packId;
    private String packTitle;
    private String description;
    private double price;
    private LocalDate launchDate;
    private boolean status;
    private String serviceType;

    // Option 1: Only feedback IDs (lightweight, safe for most APIs)
    //private List<Long> feedbackIds;

    // --- OR ---
    // Option 2: If you want feedback details:
    // private List<FeedbackDTO> feedbacks;
}
