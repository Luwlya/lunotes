package com.luwlya.lunotes.http;

import com.luwlya.lunotes.exception.AccountAlreadyExistsException;
import com.luwlya.lunotes.exception.AccountNotFoundException;
import com.luwlya.lunotes.model.Problem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Problem> handleCustomerNotFound(AccountNotFoundException exception) {
        return ResponseEntity.status(404).body(new Problem(404, "NOT_FOUND", exception.getMessage()));
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<Problem> handleCustomerAlreadyExists(AccountAlreadyExistsException exception) {
        return ResponseEntity.status(409).body(new Problem(409, "CONFLICT", exception.getMessage()));
    }
}
