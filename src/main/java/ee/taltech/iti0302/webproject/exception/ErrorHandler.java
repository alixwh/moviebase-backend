package ee.taltech.iti0302.webproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ApplicationException.class )
    public ResponseEntity<Object> handleApplicationException(ApplicationException applicationException) {
        return new ResponseEntity<>(new ErrorResponse(applicationException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
