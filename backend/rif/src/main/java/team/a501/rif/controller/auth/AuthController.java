package team.a501.rif.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.auth.LoginRequest;
import team.a501.rif.dto.auth.TokenDto;
import team.a501.rif.service.auth.AuthService;

@Tag(name = "Auth Controller", description = "인증에 관련된 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 하는 메서드입니다.")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {

        TokenDto token = authService
                .login(
                        loginRequest.getId(),
                        loginRequest.getPassword()
                );
        token.setId(loginRequest.getId());
        if (token.getAccessToken() != null) {
            LOGGER.info("[Auth] 정상적인 로그인. id :{}, token : {}"
                    , loginRequest.getId()
                    , token.getAccessToken()
            );
        }
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "액세스 토큰 재발급", description = "액세스 토큰을 재발급합니다." +
            "리프레쉬토큰이 있을 경우에는 리프래쉬 토큰 만료기간에 따라 새로운 리프래쉬나 기존 리프래쉬 토큰을 사용합니다." +
            "리프래쉬 토큰이 없으면 로그인을 요청합니다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenDto tokenDto) throws Exception {

        log.info("reissue info= {}", tokenDto);
        return ResponseEntity.ok(authService.refreshAccessToken(tokenDto));
    }
}
