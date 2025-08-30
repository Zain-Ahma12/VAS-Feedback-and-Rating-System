package com.capstone.com.vfr_backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.com.vfr_backend.Dto.AddVASPackDto;
import com.capstone.com.vfr_backend.Dto.FeedbackDto;
import com.capstone.com.vfr_backend.Dto.FeedbackandRatingsDto;
import com.capstone.com.vfr_backend.Dto.UsersDto;
import com.capstone.com.vfr_backend.model.UType.UserType;
import com.capstone.com.vfr_backend.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
// @PreAuthorize("hasRole('ADMIN')") After enabling Spring Security
public class AdminController {

    private final AdminService adminService;

    // --- User Management ---
    @GetMapping("/AllUsers")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/search")

    public ResponseEntity<List<UsersDto>> getUser(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) UserType role) {
        if (userId != null) {
            return ResponseEntity.ok(adminService.getByUserId(userId));
        }
        if (userName != null) {
            return ResponseEntity.ok(adminService.getByUserName(userName));
        }
        if (email != null) {
            return ResponseEntity.ok(adminService.getByUserEmail(email));
        }
        if (role != null) {
            return ResponseEntity.ok(adminService.getByUserRole(role));
        }
        throw new IllegalArgumentException("At least one search parameter is required");
    }

    // --- Feedback Management ---
    @GetMapping("/feedback")
    public ResponseEntity<List<FeedbackDto>> getAllFeedback() {
        return ResponseEntity.ok(adminService.getAllFeedback());
    }

    @GetMapping("/vaspacks/{id}/feedback")
    public ResponseEntity<List<FeedbackDto>> getFeedbackForPack(@PathVariable(name = "id") Long PackId) {
        return ResponseEntity.ok(adminService.getFeedbackForPack(PackId));
    }

    // --- VASPack Management ---
    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<String> deleteVasPack(@PathVariable(name = "id") Long packId) {
        return ResponseEntity.ok(adminService.deleteVasPack(packId));
    }

    @PostMapping("/VASPack/add")
    public ResponseEntity<String> addVasPack(@RequestBody AddVASPackDto addVASPackDto) {
        return ResponseEntity.ok(adminService.addVasPack(addVASPackDto));
    }

    @PatchMapping("/VASPack/{id}/update")
    public ResponseEntity<String> updateVasPack(@PathVariable(name = "id") Long packId, @RequestBody AddVASPackDto addVASPackDto) {
        return ResponseEntity.ok(adminService.updateVasPack(packId, addVASPackDto));
    }

    // --- Analytics APIs ---
    @GetMapping("/analytics/ratings/average")
    public ResponseEntity<Double> getAverageOverallRating() {
        return ResponseEntity.ok(adminService.getAverageOverallRating());
    }

    @GetMapping("/analytics/OverallRatings/vaspacks/{id}")
    public ResponseEntity<Double> getAverageOverallRatingForPack(@PathVariable(name = "id") Long vasPackId) {
        return ResponseEntity.ok(adminService.getAverageOverallRatingForPack(vasPackId));
    }

    @GetMapping("/analytics/totalFeedbackCount")
    public ResponseEntity<Long> getTotalFeedbackCount() {
        return ResponseEntity.ok(adminService.getTotalFeedbackCount());
    }

    @GetMapping("/analytics/totalUsersCount")
    public ResponseEntity<Long> getTotalUsersCount() {
        return ResponseEntity.ok(adminService.getTotalUsersCount());
    }

    @GetMapping("/analytics/totalServiceCount")
    public ResponseEntity<Long> getTotalServiceCount() {
        return ResponseEntity.ok(adminService.getTotalServiceCount());
    }

    @GetMapping("/analytics/recentFeedbacks")
    public ResponseEntity<List<FeedbackDto>> getRecentFeedback() {
        return ResponseEntity.ok(adminService.getRecentFeedback());
    }

    @GetMapping("/analytics/topFeedbacks")
    public ResponseEntity<List<FeedbackDto>> getTopFeedback() {
        return ResponseEntity.ok(adminService.getTopFeedback());
    }

    @GetMapping("/analytics/lowestFeedbacks")
    public ResponseEntity<List<FeedbackDto>> getLowestFeedback() {
        return ResponseEntity.ok(adminService.getLowestFeedback());
    }

    @GetMapping("/analytics/feedbackAndRatings")
    public ResponseEntity<List<FeedbackandRatingsDto>> getFeedbackAndRatings() {
        return ResponseEntity.ok(adminService.DisplayFeedbackandRatings());
    }

    @GetMapping("/analytics/filterFeedbacks")

    public ResponseEntity<List<FeedbackDto>> getFilteredFeedback(
            @RequestParam(required = false) Integer OverallRating,
            @RequestParam(required = false) LocalDateTime feedbackTime,
            @RequestParam(required = false) Long vasPackId) {
        if (OverallRating != null) {
            return ResponseEntity.ok(adminService.getFilteredFeedbackbyRating(OverallRating));
        }
        if (feedbackTime != null) {
            return ResponseEntity.ok(adminService.getFilteredFeedbackbyTime(feedbackTime));
        }
        if (vasPackId != null) {
            return ResponseEntity.ok(adminService.getFilteredFeedbackbyVasPack(vasPackId));
        }
        throw new IllegalArgumentException("At least one search parameter is required");
    }

    

    // // --- Future (Word Cloud & Sentiment Analysis) ---
    // @GetMapping("/analytics/feedback/wordcloud")
    // public ResponseEntity<Map<String, Integer>> getWordCloud() {
    // // generate frequency map of words
    // }

    // @GetMapping("/analytics/feedback/sentiment")
    // public ResponseEntity<Map<String, Double>> getSentimentAnalysis() {
    // // return sentiment score distribution
    // }
}
