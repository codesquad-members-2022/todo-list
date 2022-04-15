package kr.codesquad.todolist.exception;

import kr.codesquad.todolist.dto.ErrorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResult> NoSuchElementExceptionHandle(final NoSuchElementException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        log.info("ERROR {} because of {}", ex.getMessage(), ex.getCause());
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }

}
