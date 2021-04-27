package pl.jvmlab.hibernatecachedemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<Void> handleInvalidAccessException() {
        return ResponseEntity.status(UNAUTHORIZED).build();
    }

}
