package com.springboot.jwt.repository;

import com.springboot.jwt.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, String> {

    UserProfile findByEmailId(final String mailId);

    UserProfile findByUserId(final Long userId);

}
