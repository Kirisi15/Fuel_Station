package Fuel_Station.Fuel_Station.Advice;

import Fuel_Station.Fuel_Station.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(500).body(
                new MessageResponse<>(500, "Internal Server Error", null)
        );
    }
}
