package com.capstone.com.vfr_backend.Dto;

import java.time.LocalDateTime;

import com.capstone.com.vfr_backend.model.UType.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private UserType role;
    private LocalDateTime createdAt;

    // If you want feedback details also (optional, usually avoid recursion)
    //private List<Long> feedbackIds;

    
}
