package team.a501.rif.service.auth;

import team.a501.rif.dto.auth.TokenDto;

public interface AuthService {

    TokenDto login(String studentId, String password) throws RuntimeException;

    TokenDto refreshAccessToken(TokenDto tokenDto) throws Exception;
}
