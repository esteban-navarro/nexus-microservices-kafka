package com.nexus.userservice.user.controller;

import com.nexus.userservice.common.constants.ApiMessages;
import com.nexus.userservice.user.dto.request.UserRequest;
import com.nexus.userservice.user.dto.response.ApiResponse;
import com.nexus.userservice.user.dto.response.UserResponse;
import com.nexus.userservice.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(
        name = "Users",
        description = "User management endpoints."
)
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "Create a new user",
        description = "Creates a new user with the provided information."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "User created successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid user data"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "409",
                description = "Email already exists"
        )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                ApiMessages.USER_CREATED,
                response
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @Operation(
        summary = "Get all users",
        description = "Retrieves all registered users."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Users retrieved successfully"
        )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                ApiMessages.USERS_RETRIEVED,
                users
        );

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a user by its unique identifier."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "User retrieved successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "User not found"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id) {

        UserResponse response = userService.getUserById(id);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                ApiMessages.USER_RETRIEVED,
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
        summary = "Update a user",
        description = "Updates an existing user with the provided information."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "User updated successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "User not found"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "409",
                description = "Email already exists"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.updateUser(id, request);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                ApiMessages.USER_UPDATED,
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
        summary = "Delete a user",
        description = "Deletes an existing user by its unique identifier."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "204",
                description = "User deleted successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "User not found"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}