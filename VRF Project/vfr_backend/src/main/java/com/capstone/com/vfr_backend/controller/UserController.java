package com.capstone.com.vfr_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.com.vfr_backend.Dto.AddFeedbackDto;
import com.capstone.com.vfr_backend.Dto.FeedbackDto;
import com.capstone.com.vfr_backend.Dto.VASPackDto;
import com.capstone.com.vfr_backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;


    @GetMapping("/vaspacks")
    public ResponseEntity<List<VASPackDto>> getAllVasPacks() {
        return ResponseEntity.ok(userService.getAllVasPacks());
    }

    @GetMapping("/vaspacks/{id}")
    public ResponseEntity<VASPackDto> getVasPackById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getVasPackById(id));
    }

    @PostMapping("/vaspacks/{id}/feedback")
    public ResponseEntity<String> addFeedback(@PathVariable(value = "id") Long PackId,
            @Valid @RequestBody AddFeedbackDto addfeedbackDto) {
        return ResponseEntity.ok(userService.addFeedback(PackId, addfeedbackDto));
    }

    @PatchMapping("/feedback/{id}/update")
    public ResponseEntity<String> updateFeedback(@PathVariable(name="id") Long feedbackId, @RequestBody AddFeedbackDto addfeedbackDto) {
        return ResponseEntity.ok(userService.updateFeedback(feedbackId, addfeedbackDto));
    }

    // not getting useId in postman
    @GetMapping("/{userId}/feedback")
    public ResponseEntity<List<FeedbackDto>> getUserFeedback(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserFeedback(userId));
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteFeedback(id));
    }

}
