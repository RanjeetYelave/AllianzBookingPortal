package com.grooming.blog.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();

		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {

			String field = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			errorMap.put(field, defaultMessage);
		});
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<ApiResponse> TransactionSystemExceptionHandler(TransactionSystemException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionSystemException.class)
	ResponseEntity<ApiResponse> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	ResponseEntity<ApiResponse> MissingPathVariableExceptionHandler(MissingPathVariableException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<ApiResponse> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.BAD_REQUEST);
	}
}
