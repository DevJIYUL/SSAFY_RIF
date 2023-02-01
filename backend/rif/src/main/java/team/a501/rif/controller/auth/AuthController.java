package team.a501.rif.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.domain.auth.Token;

import team.a501.rif.dto.auth.LoginRequest;
import team.a501.rif.service.auth.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    // todo security에서 설정해주는 엔드포인트가 있는지 찾아보자

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequest loginRequest) {

        Token token = authService
                .login(
                        loginRequest.getId(),
                        loginRequest.getPassword()
                );

        if (token.getAccessToken() != null) {
            LOGGER.info("[Auth] 정상적인 로그인. id :{}, token : {}"
                    , loginRequest.getId()
                    , token.getAccessToken()
            );
        }
        return token;
    }
}
