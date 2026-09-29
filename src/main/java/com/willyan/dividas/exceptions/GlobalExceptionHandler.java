package com.willyan.dividas.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleDebtNotFound(ResourceNotFoundException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception,
			HttpServletRequest request) {

		List<ApiError.FieldError> errors = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> new ApiError.FieldError(error.getField(), error.getDefaultMessage()))
				.toList();

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Erro de validação nos campos enviados.", request.getRequestURI(), LocalDateTime.now(), errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
