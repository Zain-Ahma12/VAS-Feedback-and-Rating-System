package com.capstone.com.vfr_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.capstone.com.vfr_backend.Dto.AddVASPackDto;
import com.capstone.com.vfr_backend.Dto.FeedbackDto;
import com.capstone.com.vfr_backend.Dto.FeedbackandRatingsDto;
import com.capstone.com.vfr_backend.Dto.UsersDto;
import com.capstone.com.vfr_backend.model.Feedback;
import com.capstone.com.vfr_backend.model.UType.UserType;
import com.capstone.com.vfr_backend.model.Users;
import com.capstone.com.vfr_backend.model.VASPack;
import com.capstone.com.vfr_backend.repository.FeedbackRepository;
import com.capstone.com.vfr_backend.repository.UsersRepository;
import com.capstone.com.vfr_backend.repository.VASPackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UsersRepository usersRepository;
    private final VASPackRepository vasPackRepository;
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;


    public List<UsersDto> getAllUsers() {
        List<Users> userList = usersRepository.findAll();
        return userList.stream()
                .map(u -> modelMapper.map(u, UsersDto.class))
                .toList();
    }

    public List<UsersDto> getByUserId(Long userId) {
        List<Users> userList = usersRepository.findByuserId(userId);
        return userList.stream()
                .map(u -> modelMapper.map(u, UsersDto.class))
                .toList();
    }

    public List<UsersDto> getByUserEmail(String email) {
        List<Users> userList = usersRepository.findByEmail(email);
        return userList.stream()
                .map(u -> modelMapper.map(u, UsersDto.class))
                .toList();
    }

    public List<UsersDto> getByUserName(String userName) {
        List<Users> userList = usersRepository.findByusername(userName);
        return userList.stream()
                .map(u -> modelMapper.map(u, UsersDto.class))
                .toList();
    }

    public List<UsersDto> getByUserRole(UserType role) {
        List<Users> userList = usersRepository.findByrole(role);
        return userList.stream()
                .map(u -> modelMapper.map(u, UsersDto.class))
                .toList();
    }
    public List<FeedbackDto> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public List<FeedbackDto> getFeedbackForPack(Long packId) {
        List<Feedback> packFeedbackList = vasPackRepository.findById(packId).get().getFeedbacks();
        return packFeedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();

    }

    public Double getAverageOverallRating() {
        return feedbackRepository.findAverageOverallRating().orElse(0.0);
    }

    public Double getAverageOverallRatingForPack(Long vasPackId) {
        if (vasPackId == null || vasPackId <= 0) {
            throw new IllegalArgumentException("Invalid vasPackId: " + vasPackId);
        }
        return feedbackRepository.findAverageOverallRatingByVasPackId(vasPackId).orElse(0.0);
    }

    public Long getTotalFeedbackCount() {
        return feedbackRepository.count();
    }

    public Long getTotalUsersCount() {
        return usersRepository.count();
    }

    public Long getTotalServiceCount() {
        return vasPackRepository.count();
    }

    public String deleteVasPack(Long packId) {
        if (feedbackRepository.existsById(packId)) {
            feedbackRepository.deleteById(packId);
            return "Feedback deleted successfully";
        } else {
            throw new RuntimeException("Feedback not found with ID: " + packId);
        }
        
    }

    public String addVasPack(AddVASPackDto addVASPackDto) {
        VASPack vasPack = modelMapper.map(addVASPackDto, VASPack.class);
        vasPackRepository.save(vasPack);
        return "VAS Pack added";
    }

    public List<FeedbackDto> getRecentFeedback() {
        List<Feedback> feedbackList = feedbackRepository.FindRecentFeedbacks();
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public List<FeedbackDto> getTopFeedback() {
        List<Feedback> feedbackList = feedbackRepository.FindTop3Feedbacks();
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    
    
}
    public List<FeedbackDto> getLowestFeedback() {
        List<Feedback> feedbackList = feedbackRepository.FindLowest3Feedbacks();
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public List<FeedbackandRatingsDto> DisplayFeedbackandRatings() {
        List<FeedbackandRatingsDto> feedbackList = feedbackRepository.DisplayFeedbackandRatings();
        return feedbackList;
    }

    public List<FeedbackDto> getFilteredFeedbackbyRating(Integer overallRating) {
        List<Feedback> feedbackList = feedbackRepository.findByOverallRating(overallRating);
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public List<FeedbackDto> getFilteredFeedbackbyTime(LocalDateTime feedbackTime) {
        List<Feedback> feedbackList = feedbackRepository.findByFeedbackTime(feedbackTime);
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public List<FeedbackDto> getFilteredFeedbackbyVasPack(Long packId) {
        List<Feedback> feedbackList = feedbackRepository.findByVasPack_PackId(packId);
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public String updateVasPack(Long packId, AddVASPackDto addVASPackDto) {
        VASPack vasPack = vasPackRepository.findById(packId).orElseThrow(() -> new IllegalArgumentException("No VASPack found with id: " + packId));
        vasPack.setStatus(addVASPackDto.isStatus());
        return "VAS Pack updated successfully";
    }
}
