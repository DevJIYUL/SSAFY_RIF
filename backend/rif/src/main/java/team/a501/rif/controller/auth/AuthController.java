package team.a501.rif.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.a501.rif.domain.auth.Token;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.member.LoginDto;
import team.a501.rif.service.auth.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

//    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/login")
    public Token login(@RequestBody LoginDto loginDto){
        log.info("login info={}",loginDto);
        Token token = authService.login(loginDto.getStudentId(), loginDto.getPassword());
        if(token.getAccessToken() != null) log.info("[Auth] 정상적인 로그인. id :{}, token : {}",loginDto.getStudentId(),token.getAccessToken());
        return token;
    }
}
