package com.springboot.jwt.controller;

import com.springboot.jwt.DataVO.BaseVO;
import com.springboot.jwt.DataVO.ResponseVO;
import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.constant.MessageConstants;
import com.springboot.jwt.constant.ResponseStatus;
import com.springboot.jwt.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseVO registerUser(@RequestBody final UserProfileVO userProfileVO) {
        final ResponseVO responseVO = new ResponseVO();
        try {
            final BaseVO response = this.userService.registerUser(userProfileVO);
            super.success(responseVO, response, MessageConstants.USER_REGISTER_SUCCESS);
        } catch (final NumberFormatException e) {
            super.error(responseVO, ResponseStatus.USER_PHONE_NUMBER_NOT_VALID.getCode(), ResponseStatus.USER_PHONE_NUMBER_NOT_VALID.getMessage(), e);
        } catch (final DataIntegrityViolationException e) {
            super.error(responseVO, ResponseStatus.USER_MAIL_ID_EXISTS.getCode(), ResponseStatus.USER_MAIL_ID_EXISTS.getMessage(), e);
        } catch (final Exception e) {
            super.userErrorHandler(responseVO, e);
        }
        return responseVO;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseVO authenticateUser(@RequestBody final UserProfileVO userProfileVO) {
        final ResponseVO responseVO = new ResponseVO();
        try {
            final BaseVO response = this.userService.getUser(userProfileVO);
            super.success(responseVO, response, MessageConstants.USER_AUTHENTICATE_SUCCESS);
        } catch (final BadCredentialsException | InternalAuthenticationServiceException e) {
            super.error(responseVO, ResponseStatus.BAD_CREDENTIALS.getCode(), ResponseStatus.BAD_CREDENTIALS.getMessage(), e);
        } catch (final Exception e) {
            super.userErrorHandler(responseVO, e);
        }
        return responseVO;
    }
}
