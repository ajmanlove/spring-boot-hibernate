package com.ajmanlove.spring.config;

import com.ajmanlove.spring.structs.RestErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<RestErrorResponse> handleIllegal(IllegalArgumentException err) {
    return ResponseEntity.status(400).body(
      new RestErrorResponse()
        .setMessage(err.getMessage())
        .setStatus(400)
    );
  }
}
