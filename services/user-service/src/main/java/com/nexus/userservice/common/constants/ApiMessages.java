package com.nexus.userservice.common.constants;

/**
 * Centralized API messages used across the application.
 *
 * Keeping messages in a single place avoids duplicated literals,
 * improves maintainability and simplifies future localization.
 */
public final class ApiMessages {

    private ApiMessages() {
    }

    public static final String USER_CREATED = "User created successfully.";
    public static final String USERS_RETRIEVED = "Users retrieved successfully.";
    public static final String USER_RETRIEVED = "User retrieved successfully.";
    public static final String USER_UPDATED = "User updated successfully.";
    public static final String USER_NOT_FOUND = "User not found.";

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists.";
}