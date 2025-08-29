package com.capstone.com.vfr_backend.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name = "Overall_rating",columnDefinition = "TINYINT(1)")
    private Integer overallRating; // Over all rating

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name = "Quality_of_Service_rating",columnDefinition = "TINYINT(1)")
    private Integer qsRating; // Quality of Service rating

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name = "Value_for_Money_rating", columnDefinition = "TINYINT(1)")
    private Integer vmRating; // Value for Money rating

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name = "Customer_Support_rating", columnDefinition = "TINYINT(1)")
    private Integer csRating; //Customer Support rating

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name= "Ease_of_Use_rating" ,columnDefinition = "TINYINT(1)")
    private Integer euRating; //Ease of Use rating

    @Size(max = 1000, message = "Comment must not exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Size(max = 100)
    @Column(columnDefinition = "TEXT")
    private String pros;

    @Size(max = 100)
    @Column(columnDefinition = "TEXT")
    private String cons;

    @CreationTimestamp
    @Column(name = "feedback_time", nullable = false, updatable = false)
    private LocalDateTime FeedbackTime;

    // Many Feedback can have one Users
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)// owning side
    private Users users;

    // Many Feedback can have one VASPack
    @ManyToOne
    @JoinColumn(name = "vas_pack_id", nullable=false)// owning side
    private VASPack vasPack;
    
}
