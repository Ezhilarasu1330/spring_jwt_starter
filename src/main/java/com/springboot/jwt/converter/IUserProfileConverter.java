package com.springboot.jwt.converter;

import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.entity.UserProfile;

public interface IUserProfileConverter {

    void convertUserProfile(final UserProfileVO data, final UserProfile empty);

    void convertUserProfileVO(final UserProfile data, final UserProfileVO empty);

    void convertUpdateUserProfile(final UserProfileVO data, final UserProfile empty);

    void convertUpdateUserPassword(final UserProfileVO data, final UserProfile empty);
}
