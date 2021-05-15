package com.springboot.jwt.service;

import com.springboot.jwt.DataVO.BaseVO;
import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.entity.UserProfile;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface ITokenService {

    String generateToken(final UserProfile userProfile);

    BaseVO validateToken(final UserProfileVO userProfileVO);

    PasswordEncoder passwordEncoder();
}
