package com.springboot.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jwt.DataVO.ResponseVO;
import com.springboot.jwt.DataVO.UserProfileVO;
import com.springboot.jwt.constant.ResponseStatus;
import com.springboot.jwt.service.ITokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private ITokenService tokenService;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        final Integer tokenSubStringIndex = 7;
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            Long userId = null;
            UserProfileVO userProfileVO = new UserProfileVO();
            final String token = authorizationHeader.substring(tokenSubStringIndex);
            userProfileVO.setToken(token);
            try {
                userProfileVO = (UserProfileVO) this.tokenService.validateToken(userProfileVO);
                userId = userProfileVO.getUserId();
            } catch (final ExpiredJwtException e) {
                response.reset();
                objectMapper.writeValue(response.getWriter(), this.customExceptionHandler(ResponseStatus.TOKEN_EXPIRED.getCode(), ResponseStatus.TOKEN_EXPIRED.getMessage()));
            }
        }
    }

    private ResponseVO customExceptionHandler(final int code, final String message) {
        final ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(code);
        responseVO.setStatus("error");
        responseVO.setMessage(message);
        return responseVO;
    }
}
