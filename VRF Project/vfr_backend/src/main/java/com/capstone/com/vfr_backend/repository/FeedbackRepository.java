package com.capstone.com.vfr_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.com.vfr_backend.model.Feedback;

public interface  FeedbackRepository extends JpaRepository<Feedback, Long>{

    List<Feedback> findByUsers_UserId(Long userId);

    @Query("SELECT AVG(f.overallRating) FROM Feedback f")
    Optional<Double> findAverageOverallRating();

    @Query(value = "SELECT AVG(overallRating) FROM feedback WHERE vas_pack_id = ?1", nativeQuery = true)
    Optional<Double> findAverageOverallRatingByVasPackId(Long vasPackId);
    
}
