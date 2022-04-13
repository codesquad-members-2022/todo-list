package com.todolist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestResponse>> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        // 인스턴스 변수로 변경 고려 -> 여러 개의 예외가 발생할 경우에는 ?
        List<RestResponse> responses = new ArrayList<>();

        for (FieldError error : exception.getFieldErrors()) {
            log.error("errorFieldName : {}, error message : {}", error.getField(), error.getDefaultMessage());

            RestResponse restResponse = RestResponse.methodArgumentNotValidException(error.getField(), error.getDefaultMessage());
            responses.add(restResponse);
        }

        return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<List<RestResponse>> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        List<RestResponse> responses = new ArrayList<>();
        String errorFieldName = exception.getName();

        // MethodArgumentTypeMismatchException 클래스에 따로 errorMessage 받아오는 메서드가 없어서 직접 Enum 에서 받아서 넣음
        String errorMessage = ExceptionType.CARD_TYPE_MISMATCH.getMessage();

        log.error("errorFieldName : {}, errorMessage : {}", errorFieldName, errorMessage);

        RestResponse restResponse = RestResponse.methodArgumentTypeMismatchException(errorFieldName, errorMessage);
        responses.add(restResponse);

        return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
    }

    // cardService.path() try-catch 부분에서 예외 캐치
    @ExceptionHandler(NotFoundCardException.class)
    public ResponseEntity<List<RestResponse>> NotFoundCardException(NotFoundCardException exception) {
        List<RestResponse> responses = new ArrayList<>();
        String errorFiledName = exception.getErrorFiledName();

        // 직접 만든 커스텀 예외라서 에러 메세지 받아오는 메서드 따로 만들었음
        String errorMessage = exception.getExceptionType();

        log.error("errorFiledName = {}, error message = {}", errorFiledName, errorMessage);

        RestResponse restResponse = RestResponse.notFoundCardException(errorFiledName, errorMessage);
        responses.add(restResponse);

        return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
    }
}
