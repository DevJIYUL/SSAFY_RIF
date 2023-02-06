package team.a501.rif.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private Integer status;
    private String message;

    private ExceptionResponse(){}

    public static ExceptionResponse from(ExceptionCode exceptionCode) {

        ExceptionResponse response = new ExceptionResponse();

        response.status = exceptionCode.getHttpStatus().value();
        response.message = exceptionCode.getMessage();

        return response;
    }

    public static ExceptionResponse of(int status, String message){
        ExceptionResponse response = new ExceptionResponse();

        response.status = status;
        response.message = message;

        return response;
    }
}
