package com.jonaschagas.controller.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ValidationError err = new ValidationError("Erro de Validação", HttpStatus.BAD_REQUEST.value(),
				System.currentTimeMillis());
		for (FieldError c : e.getBindingResult().getFieldErrors()) {
			err.addError(c.getField(), c.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<StandardError> methodException(UnexpectedTypeException e, 
			HttpServletRequest request) {
		ValidationError err = new ValidationError("Erro de Validação", HttpStatus.BAD_REQUEST.value(),
				System.currentTimeMillis());
		err.addError(e.getLocalizedMessage(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> methodException(HttpMessageNotReadableException e, 
			HttpServletRequest request) {
		ValidationError err = new ValidationError("Erro de Validação", HttpStatus.BAD_REQUEST.value(),
				System.currentTimeMillis());
		err.addError("Erro", "Verifique a formatação dos dados");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	

}
