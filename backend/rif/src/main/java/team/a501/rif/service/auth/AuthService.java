package team.a501.rif.service.auth;

import team.a501.rif.domain.auth.Token;

public interface AuthService {

    Token login(String studentId,String password) throws RuntimeException;
}