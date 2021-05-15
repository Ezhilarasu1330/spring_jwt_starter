package com.springboot.jwt.converter.impl;

import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.converter.IUserProfileConverter;
import com.springboot.jwt.entity.UserProfile;
import com.springboot.jwt.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserProfileConverter implements IUserProfileConverter {

    @Autowired
    private ITokenService tokenService;

    public void convertUserProfile(final UserProfileVO data, final UserProfile empty) {
        empty.setFirstName(data.getFirstName());
        empty.setLastName(data.getLastName());
        empty.setEmailId(data.getEmailId());
        empty.setPassword(this.tokenService.passwordEncoder().encode(data.getPassword()));
        empty.setPhoneNumber(data.getPhoneNumber());
        empty.setActive(true);
        empty.setLastUpdatedTime(new Date());
        empty.setExpiryHash(UUID.randomUUID().toString());
    }

    public void convertUserProfileVO(final UserProfile data, final UserProfileVO empty) {
        empty.setUserId(data.getUserId());
        empty.setFirstName(data.getFirstName());
        empty.setLastName(data.getLastName());
        empty.setEmailId(data.getEmailId());
        empty.setActive(data.isActive());
        empty.setPassword(null);
        empty.setNewPassword(null);
        empty.setPhoneNumber(data.getPhoneNumber());
        empty.setToken(this.tokenService.generateToken(data));
    }

    public void convertUpdateUserProfile(final UserProfileVO data, final UserProfile empty) {
        empty.setFirstName(data.getFirstName());
        empty.setLastName(data.getLastName());
        empty.setPhoneNumber(data.getPhoneNumber());
    }

    public void convertUpdateUserPassword(final UserProfileVO data, final UserProfile empty) {
        empty.setPassword(this.tokenService.passwordEncoder().encode(data.getNewPassword()));
        empty.setExpiryHash(UUID.randomUUID().toString());
    }
}
