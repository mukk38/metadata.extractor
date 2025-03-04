package tr.com.muskar.metadata.extractor.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private final String TIMESTAMP_KEY_VALUE = "timestamp";
    private final String MESSAGE_KEY_VALUE = "message";

    @ExceptionHandler(FileCouldNotBeFoundException.class)
    public ResponseEntity<Object> fileCouldNotBeFoundException(
            FileCouldNotBeFoundException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_KEY_VALUE, LocalDateTime.now());
        body.put(MESSAGE_KEY_VALUE, "Dosya Bulunamadı :" + exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CouldNotExtractTextFromFileException.class)
    public ResponseEntity<Object> couldNotExtractTextFromFileException(CouldNotExtractTextFromFileException couldNotExtractTextFromFileException, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_KEY_VALUE, LocalDateTime.now());
        body.put(MESSAGE_KEY_VALUE, couldNotExtractTextFromFileException.getMessage() + " Tika Exception");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
