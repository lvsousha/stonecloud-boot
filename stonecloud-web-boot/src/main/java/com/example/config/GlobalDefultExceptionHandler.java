package com.example.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.globalException.ApiException;

@ControllerAdvice
public class GlobalDefultExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(GlobalDefultExceptionHandler.class);
	
	@ExceptionHandler(ApiException.class)  
    @ResponseBody  
    public JSONObject defultExcepitonHandler(HttpServletRequest request,ApiException e) {  
        log.error("ApiException");
		return e.getResult();  
    }
	
	@ExceptionHandler(Exception.class)  
    public Object defultExcepitonHandler(HttpServletRequest req, Exception e) throws IOException {  
		String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
        	log.info(ApiException.ERROR.toString());
        	return new ResponseEntity<>(ApiException.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("detailMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            modelAndView.addObject("url", req.getRequestURL());
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }
	
}
