package team.a501.rif.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.auth.TokenDto;

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
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {

        TokenDto token = authService
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
        return ResponseEntity.ok(token);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenDto tokenDto) throws Exception {
        return ResponseEntity.ok(authService.refreshAccessToken(tokenDto));
    }
}