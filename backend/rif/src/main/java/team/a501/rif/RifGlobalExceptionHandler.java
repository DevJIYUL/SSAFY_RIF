package team.a501.rif;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import team.a501.rif.exception.ExceptionCode;
import team.a501.rif.exception.ExceptionResponse;
import team.a501.rif.exception.RifCustomException;

@RestControllerAdvice
@Slf4j
public class RifGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 여기서 모든 RuntimeException을 다 처리하도록 한다
    @ExceptionHandler(RifCustomException.class)
    public ResponseEntity<ExceptionResponse> handleRifCustomException(RifCustomException e) {

        ExceptionCode code = e.getExceptionCode();

        return ResponseEntity.status(code.getHttpStatus()).body(ExceptionResponse.from(code));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {

        Integer value = HttpStatus.BAD_REQUEST.value();

        ExceptionResponse response = ExceptionResponse.of(value, e.getMessage());

        return ResponseEntity.status(value).body(response);
    }
}
