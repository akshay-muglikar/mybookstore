package com.mystore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mystore.dto.ErrorResponse;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	  public ErrorResponse defaultErrorHandler() throws Exception {
		return new ErrorResponse("Failure","Please try after some time");
	}
}