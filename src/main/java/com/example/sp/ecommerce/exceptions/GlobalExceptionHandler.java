package com.example.sp.ecommerce.exceptions;

import com.example.sp.ecommerce.payload.APIResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse> constraintViolationExceptionMethodHandler(ConstraintViolationException ex)
    {
        String response = ex.getConstraintViolations()
                .stream()
                .map(constraintViolationException -> constraintViolationException.getPropertyPath() + ": " + constraintViolationException.getMessage())
                .collect(Collectors.joining(", "));

        APIResponse apiResponse = new APIResponse(response, false);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
           String fieldName = ((FieldError)error).getField();
           String message = error.getDefaultMessage();

           response.put(fieldName, message);
        });

        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex)
    {
        String message = getRootCauseMessage(ex);
        return new ResponseEntity<APIResponse>(new APIResponse(message, false), HttpStatus.BAD_REQUEST);
    }

    private String getRootCauseMessage(DataIntegrityViolationException ex)
    {
        String rootCauseMessage = ex.getRootCause() != null
                                        ? ex.getMessage().toLowerCase()
                                        : ex.getMessage();

        if (rootCauseMessage.contains("unique") || rootCauseMessage.contains("primary key"))
        {
            return "Unique index or primary key violation";
        }
        else if (rootCauseMessage.contains("foreign key"))
        {
            return "Foreign key constraint violated";
        }
        else if (rootCauseMessage.contains("null not allowed") || rootCauseMessage.contains("not-null"))
        {
            return "Required field missing";
        }

        return rootCauseMessage;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(new APIResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> apiExceptionHandler(APIException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(new APIResponse(message, false), HttpStatus.BAD_REQUEST);
    }

}
