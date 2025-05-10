package com.jsl.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsl.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	ResponseEntity<ErrorResponse> handleApiException(ApiException ex){
		
		ErrorResponse error = new ErrorResponse(ex.getMessage(), LocalDateTime.now()) ;
		
		return ResponseEntity.badRequest().body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
		
		String errorMessage = ex.getBindingResult()
						 .getFieldErrors()
						 .stream()
						 .map(fieldError -> fieldError.getField() + ": "+ fieldError.getDefaultMessage())
						 .collect(Collectors.joining(", "));
		
		ErrorResponse error = new ErrorResponse(errorMessage, LocalDateTime.now());
		
		return ResponseEntity.badRequest().body(error);
		
	}
	
	@ExceptionHandler
	ResponseEntity<ErrorResponse>handleOtherExceptions(Exception ex){
		ErrorResponse error = new ErrorResponse("Something is wrong", LocalDateTime.now());
		
		return ResponseEntity.internalServerError().body(error);
		
	}

}
