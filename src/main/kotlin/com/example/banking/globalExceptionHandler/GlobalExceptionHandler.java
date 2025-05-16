package com.example.banking.globalExceptionHandler;

import com.example.banking.customExceptionHandler.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFound(AccountNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("status", 404);
        body.put("timestamp", Instant.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
