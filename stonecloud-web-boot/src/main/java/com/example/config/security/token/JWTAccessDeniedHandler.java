package com.example.config.security.token;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.alibaba.fastjson.JSONObject;
import com.example.config.exception.AppException;
import com.example.config.exception.Result;


public class JWTAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter()
        .write(JSONObject.toJSONString(Result.failed(new AppException(com.example.config.exception.ErrorCode.ACCESS_DENIED))));
    response.flushBuffer();
  }
}
