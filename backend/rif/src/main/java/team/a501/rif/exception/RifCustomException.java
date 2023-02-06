package team.a501.rif.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RifCustomException extends RuntimeException{
    private final ExceptionCode exceptionCode;

}
