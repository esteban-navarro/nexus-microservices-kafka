package com.nexus.userservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.userservice.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    
}