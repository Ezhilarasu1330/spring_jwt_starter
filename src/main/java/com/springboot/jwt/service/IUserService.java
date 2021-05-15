package com.springboot.jwt.service;

import com.springboot.jwt.DataVO.BaseVO;
import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.exception.ApplicationError;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    BaseVO registerUser(final UserProfileVO userProfileVO) throws ApplicationError;

    BaseVO getUser(final UserProfileVO userProfileVO) throws ApplicationError;

}
