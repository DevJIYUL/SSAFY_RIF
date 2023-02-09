package team.a501.rif.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    ENTITY_INSTANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 엔티티 인스턴스입니다"),
    NOT_ENOUGH_POINTS(HttpStatus.OK, "포인트가 부족합니다"),

    DIV_BY_ZERO(HttpStatus.BAD_REQUEST, "0으로 나눌 수 없습니다");
    private final HttpStatus httpStatus;
    private final String message;
}
