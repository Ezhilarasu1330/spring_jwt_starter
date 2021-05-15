package com.springboot.jwt.service.impl;

import com.springboot.jwt.DataVO.BaseVO;
import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.constant.MessageConstants;
import com.springboot.jwt.converter.IUserProfileConverter;
import com.springboot.jwt.entity.UserProfile;
import com.springboot.jwt.exception.ApplicationError;
import com.springboot.jwt.repository.UserProfileRepo;
import com.springboot.jwt.service.IUserService;
import com.springboot.jwt.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserProfileRepo userProfileRepo;

    @Autowired
    private IUserProfileConverter userProfileConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public BaseVO registerUser(final UserProfileVO userProfileVO) throws ApplicationError {
        if (userProfileVO.getFirstName() == null || userProfileVO.getFirstName().equals("")) {
            throw new ApplicationError(MessageConstants.USER_FIRST_NAME_NULL);
        } else if (userProfileVO.getLastName() == null || userProfileVO.getLastName().equals("")) {
            throw new ApplicationError(MessageConstants.USER_LAST_NAME_NULL);
        } else if (userProfileVO.getEmailId() == null || userProfileVO.getEmailId().equals("")) {
            throw new ApplicationError(MessageConstants.USER_MAIL_ID_NULL);
        } else if (userProfileVO.getPhoneNumber() == null || userProfileVO.getPhoneNumber().equals("")) {
            throw new ApplicationError(MessageConstants.USER_PHONE_NUMBER_NULL);
        } else if (userProfileVO.getPassword() == null || userProfileVO.getPassword().equals("")) {
            throw new ApplicationError(MessageConstants.USER_PASSWORD_NULL);
        } else if (!Validator.mailValidator(userProfileVO.getEmailId())) {
            throw new ApplicationError(MessageConstants.USER_MAIL_ID_NOT_VALID);
        } else if (!Validator.passwordValidator(userProfileVO.getPassword())) {
            throw new ApplicationError(MessageConstants.USER_PASSWORD_WEAK);
        }
        Long.parseLong(userProfileVO.getPhoneNumber());
        final UserProfile userProfile = new UserProfile();
        this.userProfileConverter.convertUserProfile(userProfileVO, userProfile);
        this.userProfileRepo.save(userProfile);
        return null;
    }

    @Override
    public BaseVO getUser(final UserProfileVO userProfileVO) throws ApplicationError {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userProfileVO.getEmailId(), userProfileVO.getPassword()));
        final UserProfile userProfile = this.userProfileRepo.findByEmailId(userProfileVO.getEmailId());
        if (!userProfile.isActive()) {
            throw new ApplicationError(MessageConstants.ACC_DISABLED);
        }
        this.userProfileConverter.convertUserProfileVO(userProfile, userProfileVO);
        return userProfileVO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
