package bangwool.server.controller;

import bangwool.server.dto.response.ErrorResponse;
import bangwool.server.exception.BangwoolException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BangwoolException.class)
    public ResponseEntity<ErrorResponse> processValidationError(BangwoolException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processMethodArgumentError(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        int errorCode = 1004;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorCode, errorCode + errorMessage));
    }
}
