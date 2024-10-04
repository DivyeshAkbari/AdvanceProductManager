package co.pragra.productmanager.newproductmanager.rest;

import co.pragra.productmanager.newproductmanager.dto.ErrorDto;
import co.pragra.productmanager.newproductmanager.exception.InvalidReviewException;
import co.pragra.productmanager.newproductmanager.exception.InvalidUserDataException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;


@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(InvalidReviewException.class)
    public ResponseEntity<ErrorDto> handleBadReview(Exception ex)
    {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder().
                errorCode(400).errorMessage(ex.getMessage()).TimeStamp(new Date()).build());
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<ErrorDto> handleBadUser(Exception ex)
    {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder().
                errorCode(404).errorMessage(ex.getMessage()).TimeStamp(new Date()).build());
    }
}
