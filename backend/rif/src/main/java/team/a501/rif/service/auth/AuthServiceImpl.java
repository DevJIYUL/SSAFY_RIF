package team.a501.rif.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.a501.rif.config.Jwt.JwtTokenProvider;
import team.a501.rif.domain.auth.Token;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Transactional
    @Override
    public Token login(String studentId, String password) {
        log.info("studentId info = {}",studentId);
        log.info("password info = {}",password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(studentId,password);
        log.info("authenticationToken info = {}",authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("authentication info={}",authentication);
        Token token = jwtTokenProvider.issueToken(authentication);
        log.info("Token info={}",token);
        return token;
    }
}
