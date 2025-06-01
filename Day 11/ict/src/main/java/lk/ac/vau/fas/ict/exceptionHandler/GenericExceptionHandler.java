package lk.ac.vau.fas.ict.exceptionHandler;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lk.ac.vau.fas.ict.model.ErrorResponce;

@RestControllerAdvice
public class GenericExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponce> handleEntityNotFound(EntityNotFoundException exception) {
		ErrorResponce errorResponce = new ErrorResponce(HttpStatus.NOT_FOUND.value(), 
				exception.getMessage());
		return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ErrorResponce> handleDuplicateKey(DuplicateKeyException exception) {
		ErrorResponce errorResponce = new ErrorResponce(HttpStatus.BAD_REQUEST.value(), 
				exception.getMessage());
		return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponce> handleCommonExpections(Exception exception) {
		ErrorResponce errorResponce = new ErrorResponce(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				exception.getMessage());
		return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
