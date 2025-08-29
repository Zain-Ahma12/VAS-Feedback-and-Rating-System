package com.capstone.com.vfr_backend.Dto;

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
public class AddFeedbackDto {
    private Integer OverallRating;
    private Integer qsRating;
    private Integer vmRating;
    private Integer csRating;
    private Integer euRating;
    private String comment;
    private String pros;
    private String cons;
    

    // Instead of embedding full User and VASPack (to avoid recursion)
    private Long userId;
    private Long vasPackId;
}
