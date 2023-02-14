package team.a501.rif.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private Integer status;
    private String message;

    private ExceptionResponse() {}

    public static ExceptionResponse from(ErrorCode errorCode) {

        ExceptionResponse response = new ExceptionResponse();

        response.status = errorCode.getHttpStatus().value();
        response.message = errorCode.getMessage();

        return response;
    }

    public static ExceptionResponse of(int status, String message) {
        ExceptionResponse response = new ExceptionResponse();

        response.status = status;
        response.message = message;

        return response;
    }
}
