package com.SmartWatch.controller;

import com.SmartWatch.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFound() {
        ErrorResponse errorResponse=new ErrorResponse("NOT_FOUND", "Đường dẫn không tồn tại.");
        return ResponseEntity.status(404).body(errorResponse);
    }
}
