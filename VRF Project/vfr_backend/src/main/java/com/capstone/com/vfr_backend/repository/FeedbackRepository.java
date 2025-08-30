package com.capstone.com.vfr_backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.com.vfr_backend.Dto.FeedbackandRatingsDto;
import com.capstone.com.vfr_backend.model.Feedback;

public interface  FeedbackRepository extends JpaRepository<Feedback, Long>{

    List<Feedback> findByUsers_UserId(Long userId);

    @Query("SELECT AVG(f.overallRating) FROM Feedback f")
    Optional<Double> findAverageOverallRating();

    @Query(value = "SELECT AVG(overall_rating) FROM feedback WHERE vas_pack_id = ?1", nativeQuery = true)
    Optional<Double> findAverageOverallRatingByVasPackId(Long vasPackId);
    
    @Query(value = "SELECT * FROM feedback ORDER BY feedback_time DESC LIMIT 5", nativeQuery=true)
    List<Feedback>  FindRecentFeedbacks();

    @Query(value = "SELECT * FROM feedback ORDER BY Overall_rating DESC, feedback_time DESC LIMIT 3;", nativeQuery=true)
    List<Feedback> FindTop3Feedbacks();

    @Query(value = "SELECT * FROM feedback ORDER BY Overall_rating ASC, feedback_time ASC LIMIT 3;", nativeQuery=true)
    List<Feedback> FindLowest3Feedbacks();

    @Query(value = "SELECT Overall_rating, COUNT(*) AS total_feedbacks FROM feedback GROUP BY Overall_rating ORDER BY Overall_rating DESC;", nativeQuery=true)
    List<FeedbackandRatingsDto> DisplayFeedbackandRatings();

    public List<Feedback> findByFeedbackTime(LocalDateTime feedbackTime);

    public List<Feedback> findByVasPack_PackId(Long packId);

    public List<Feedback> findByOverallRating(Integer overallRating);


}
