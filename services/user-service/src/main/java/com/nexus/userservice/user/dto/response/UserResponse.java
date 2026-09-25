package com.nexus.userservice.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}