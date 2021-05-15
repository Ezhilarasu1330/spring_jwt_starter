package com.springboot.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jwt.DataVO.ResponseVO;
import com.springboot.jwt.constant.ResponseStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppAccessDeniedHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

    final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AccessDeniedException e) throws IOException {
        httpServletResponse.reset();
        httpServletResponse.setContentType("application/json");
        mapper.writeValue(httpServletResponse.getWriter(), this.CustomForbiddenHandler(ResponseStatus.FORBIDDEN_ACCESS.getMessage()));
    }

    @Override
    public void commence(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AuthenticationException e) throws IOException {
        httpServletResponse.reset();
        httpServletResponse.setContentType("application/json");
        mapper.writeValue(httpServletResponse.getWriter(), this.CustomForbiddenHandler(ResponseStatus.FORBIDDEN_ACCESS.getMessage()));
    }

    private ResponseVO CustomForbiddenHandler(final String message) {
        final ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(ResponseStatus.FORBIDDEN_ACCESS.getCode());
        responseVO.setMessage(message);
        responseVO.setStatus("error");
        return responseVO;
    }
}
