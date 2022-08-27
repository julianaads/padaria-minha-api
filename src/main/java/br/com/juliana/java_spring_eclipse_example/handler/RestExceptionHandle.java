package br.com.juliana.java_spring_eclipse_example.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.juliana.java_spring_eclipse_example.model.error.ErrorMessage;
import br.com.juliana.java_spring_eclipse_example.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandle {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value() , ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
