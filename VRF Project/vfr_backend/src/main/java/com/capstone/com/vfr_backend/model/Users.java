package com.capstone.com.vfr_backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.capstone.com.vfr_backend.model.UType.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Name cannot be empty")
    @Column(nullable=false, length=100)
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true, nullable=false, length=100)
    @Email
    private String email;

    @Column(nullable = false)
    @NotNull
    @Size(min = 8)
    private String password;

    @Column(name= "phone_number", nullable = false)
    @NotNull
    @Size(max = 10)
    private String phone; 

    @Enumerated(EnumType.STRING)
    private UserType role; // e.g., "USER" or "ADMIN"

    @CreationTimestamp
    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;
    
    // One User can have Many Feedback
    @OneToMany(mappedBy = "users") //inverse side
    private List<Feedback> feedback =new ArrayList<>();
    

    
}

