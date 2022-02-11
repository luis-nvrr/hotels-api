package meli.challenge.quality.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import meli.challenge.quality.application.dtos.ExceptionResponse;
import meli.challenge.quality.domain.exceptions.InvalidDateException;

@ControllerAdvice
public class ExceptionAdvice {
  @ExceptionHandler(InvalidDateException.class)
  public ResponseEntity<ExceptionResponse> handle(InvalidDateException e) {
    ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
