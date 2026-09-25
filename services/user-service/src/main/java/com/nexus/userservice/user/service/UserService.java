package com.nexus.userservice.user.service;

import com.nexus.userservice.common.constants.ApiMessages;
import com.nexus.userservice.user.dto.request.UserRequest;
import com.nexus.userservice.user.dto.response.UserResponse;
import com.nexus.userservice.user.entity.User;
import com.nexus.userservice.user.exception.EmailAlreadyExistsException;
import com.nexus.userservice.user.exception.UserNotFoundException;
import com.nexus.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(ApiMessages.EMAIL_ALREADY_EXISTS);
        }

        LocalDateTime now = LocalDateTime.now();

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                ))
                .toList();
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }


    public UserResponse updateUser(Long id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(ApiMessages.EMAIL_ALREADY_EXISTS);
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getEmail(),
                updatedUser.getCreatedAt(),
                updatedUser.getUpdatedAt()
        );
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND));

        userRepository.delete(user);
    }

}