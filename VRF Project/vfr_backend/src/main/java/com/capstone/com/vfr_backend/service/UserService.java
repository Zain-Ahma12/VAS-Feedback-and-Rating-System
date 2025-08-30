package com.capstone.com.vfr_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.capstone.com.vfr_backend.Dto.AddFeedbackDto;
import com.capstone.com.vfr_backend.Dto.FeedbackDto;
import com.capstone.com.vfr_backend.Dto.VASPackDto;
import com.capstone.com.vfr_backend.model.Feedback;
import com.capstone.com.vfr_backend.model.Users;
import com.capstone.com.vfr_backend.model.VASPack;
import com.capstone.com.vfr_backend.repository.FeedbackRepository;
import com.capstone.com.vfr_backend.repository.UsersRepository;
import com.capstone.com.vfr_backend.repository.VASPackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final VASPackRepository vasPackRepository;
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    

    public List<VASPackDto> getAllVasPacks() {
        List<VASPack> packList = vasPackRepository.findAll();
        return packList.stream()
                .map(p -> modelMapper.map(p, VASPackDto.class))
                .toList();

    }

    public VASPackDto getVasPackById(Long id) {
        VASPack pack = vasPackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No VASPack found with id: " + id));
        return modelMapper.map(pack, VASPackDto.class);
    }

    public String addFeedback(Long packId, AddFeedbackDto addfeedbackDto) {
        // 1. Find VASPack by ID
        VASPack vasPack = vasPackRepository.findById(packId)
                .orElseThrow(() -> new RuntimeException("VAS Pack not found with ID: " + packId));

        // 2. Find logged-in user (for now from DTO)
        Users user = usersRepository.findById(addfeedbackDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + addfeedbackDto.getUserId()));

        // 3. Map DTO -> Entity
        Feedback feedback = modelMapper.map(addfeedbackDto, Feedback.class);

        //4. set relationships
        feedback.setUsers(user);
        feedback.setVasPack(vasPack);
        feedback.setFeedbackTime(LocalDateTime.now());

        // 5. Save
        feedbackRepository.save(feedback);

        return "Feedback submitted successfully for VAS Pack: " + vasPack.getPackTitle();
    }

    public List<FeedbackDto> getUserFeedback(Long userId) {
        List<Feedback> feedbackList = feedbackRepository.findByUsers_UserId(userId);
        return feedbackList.stream().map(f -> modelMapper.map(f, FeedbackDto.class)).toList();
    }

    public String deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return "Feedback deleted successfully";
        } else {
            throw new RuntimeException("Feedback not found with ID: " + id);
        }
    }

    public String updateFeedback(Long feedbackId, AddFeedbackDto addfeedbackDto) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new IllegalArgumentException("No Feedback found with id: " + feedbackId));
        modelMapper.map(addfeedbackDto, feedback);
        feedbackRepository.save(feedback);
        return "Feedback updated successfully";
                
    }
}
